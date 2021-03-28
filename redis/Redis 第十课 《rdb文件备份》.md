## Redis 第十课 《集群搭建》

```shell
#!/bin/sh

#执行手动备份
msg=`redis-cli -a redispwd bgsave`

#通过info Persistence 找到rdb_bgsave_in_progress 是否执行备份完成了 1正在执行创建 0创建完成
#通过grep rdb_bgsave_in_progress 可以得到数据 rdb_bgsave_in_progres:0
#通过数据里的 :分割 打印第二列 awk -F":" '{print $2}'`  得到0或者1

result=`redis-cli -a redispwd info Persistence |grep rdb_bgsave_in_progress |awk -F":" '{print $2}'`

#循环判断是否创建完成，如果等于1 正在创建 就执行循环获取下一次得到的结果

while [ `echo  $result`  == "1" ] ;
do
  sleep 1
  result=`redis-cli -a redispwd info Persistence |grep rdb_bgsave_in_progress |awk -F":" '{print $2}'`
done

#如果等于0执行备份

dateDir=`date +%Y%m%d%H`
dateFile=`date +%M`
mkdir -p /home/redis/rdb/$dateDir
cp /root/redis-6.0.6/src/dump.rdb  /home/redis/rdb/$dateDir/$dateFile".rdb"
find /root/redis-6.0.6/src  -mmin +1 -name dump* -exec  rm -rf {} \;
```



## Redis 第十一课 《docker集群搭建》

```shell
$ docker network create redis --subnet 172.33.0.0/1
$ docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
a346cc35207f   bridge    bridge    local
104e914d1a83   host      host      local
40f97793930f   mynet     bridge    local
777c296edc22   none      null      local
1c95ff9ada98   redis     bridge    local
```

脚本部署：

```shell
#!/bin/sh
for port in $(seq 1 6);
do 
mkdir -p /mydata/redis/node-${port}/conf
touch /mydata/redis/node-${port}/conf/redis.conf
cat << EOF >/mydata/redis/node-${port}/conf/redis.conf
port 6379
bind 0.0.0.0
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 172.33.0.1${port}
cluster-announce-port 6379
cluster-announce-bus-port 16379
appendonly yes
EOF
done
```

Docker 启动

```sh
docker run -p 6376:6379 -p 16376:16379 --name redis-6 \
    -v /mydata/redis/node-6/data:/data \
    -v /mydata/redis/node-6/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.16 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
    docker run -p 6375:6379 -p 16375:16379 --name redis-5 \
    -v /mydata/redis/node-5/data:/data \
    -v /mydata/redis/node-5/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.15 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
    docker run -p 6374:6379 -p 16374:16379 --name redis-4 \
    -v /mydata/redis/node-4/data:/data \
    -v /mydata/redis/node-4/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.14 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
    docker run -p 6373:6379 -p 16373:16379 --name redis-3 \
    -v /mydata/redis/node-3/data:/data \
    -v /mydata/redis/node-3/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.13 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
    docker run -p 6372:6379 -p 16372:16379 --name redis-2 \
    -v /mydata/redis/node-2/data:/data \
    -v /mydata/redis/node-2/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.12 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
    docker run -p 6371:6379 -p 16371:16379 --name redis-1 \
    -v /mydata/redis/node-1/data:/data \
    -v /mydata/redis/node-1/conf/redis.conf:/etc/redis/redis.conf \
    -d --net redis --ip 172.33.0.11 redis:5.0.9-alpine3.11 redis-server /etc/redis/redis.conf
    
   $ redis-cli --cluster create 172.33.0.11:6379 172.33.0.12:6379 172.33.0.13:6379 172.33.0.14:6379 172.33.0.15:6379 172.33.0.16:6379 --cluster-replicas 1
```

```sh
/data # redis-cli -c
127.0.0.1:6379> cluster info
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:104
cluster_stats_messages_pong_sent:102
cluster_stats_messages_sent:206
cluster_stats_messages_ping_received:97
cluster_stats_messages_pong_received:104
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:206
127.0.0.1:6379> cluster nodes
```

