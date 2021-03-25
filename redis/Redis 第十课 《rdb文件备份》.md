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



## Redis 第十课 《集群搭建》

```shell
127.0.0.1:6379> INFO replication
# Replication
role:master  # 主机
connected_slaves:0  # 从机
master_replid:b6bab71de89b8f53d1f1b51d0a338d73fa7e6c2c
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

