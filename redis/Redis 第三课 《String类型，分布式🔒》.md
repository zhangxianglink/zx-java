## Redis 第三课 《String类型，分布式🔒》

**基本操作**

```shell
# 判断key是否存在
127.0.0.1:6379> exists key
(integer) 1  存在为1
127.0.0.1:6379> exists key2
(integer) 0  不存在为0
# 获取字符串长度
127.0.0.1:6379> strlen key
(integer) 11
# 头部添加数据
127.0.0.1:6379> get lol
"jacklove"
127.0.0.1:6379> append lol " is ad"
(integer) 14
127.0.0.1:6379> get lol
"jacklove is ad"
# 指定位置,替换字符串
127.0.0.1:6379> setrange lol 12 "adc"
(integer) 15
127.0.0.1:6379> get lol
"jacklove is adc"
# 获取数据指定长度
127.0.0.1:6379> getrange lol 0 -1  
"jacklove is adc" 全部长度
127.0.0.1:6379> getrange lol 12 15
"adc"  指定长度截取
```

**自增计数器**

```shell
# 设置初始key
127.0.0.1:6379> set wink:num 0
# 加一操作
27.0.0.1:6379> incr wink:num
(integer) 1
127.0.0.1:6379> incr wink:num
(integer) 2
127.0.0.1:6379> incr wink:num
(integer) 3
# 减一操作
127.0.0.1:6379> decr wink:num
(integer) 1
127.0.0.1:6379> decr wink:num
(integer) 0
127.0.0.1:6379> decr wink:num
(integer) -1
# 设置步长操作, 可以设置负值
127.0.0.1:6379> decrby wink:num -1000
(integer) 1096
127.0.0.1:6379> incrby wink:num -200
(integer) 896
```

**多值操作**

```shell
# many set 
127.0.0.1:6379> mset k1 "will " k2 "love " k3 "dream"
# many get 有序返回
127.0.0.1:6379> mget k2 k1 k3
1) "love "
2) "will "
3) "dream" 
# many set  if not exits
127.0.0.1:6379> msetnx  k1 "llll" k5 "happy"
(integer) 0
127.0.0.1:6379> mget k1 k2 k3
1) "will"
2) "love "
3) "happy"
k5 不存在导致所有修改失败，msetnx操作具有原子性。
```

**过期时间，分布式锁**

[拓展](https://mp.weixin.qq.com/s/X2yJlbnWSR3eOQe4ayy3Yg)

捡田螺的小男孩公众号

```shell
# 设置过期时间 set with expire
127.0.0.1:6379> SETEX key 60 "jack"
# 如果存在不做修改 set if not exits
127.0.0.1:6379> SETNX key "999"
(integer) 1  
127.0.0.1:6379> SETNX key "ooo"
(integer) 0
127.0.0.1:6379> set key "pp" 正常修改
OK
# 以上两者结合，实现分布式锁
🔒需要满足的基本操作： 加锁， 解锁， 锁超时
互斥性: 证明资源已经收到保护，需要等待
不死锁：为避免死锁，在规定单位时间结束后放开资源。
可重入：对同一个线程，可以多次重复加锁。
SETEX,SETNX 将加锁，设置超时时间分成两步，因为不是原子性操作，容易引发死锁问题,所以有了新命令。
127.0.0.1:6379> set mylock "power" ex 60 nx
EX seconds：以秒为单位设置过期时间
PX milliseconds：以毫秒为单位设置过期时间
EXAT timestamp：设置以秒为单位的UNIX时间戳所对应的时间为过期时间
PXAT milliseconds-timestamp：设置以毫秒为单位的UNIX时间戳所对应的时间为过期时间
NX：键不存在的时候设置键值
XX：键存在的时候设置键值
KEEPTTL：保留设置前指定键的生存时间

但是依旧存在问题，1.锁过期了，业务还在执行
               2. 锁过期了，被其他人占用，你业务完成了，把锁干掉了

解决方案：给锁一个，随机数字做value，解锁之前做一下判断（为保障判断+解锁的原子性 lua 脚本实现）
上述方案解决了第二个问题，引入redisson,加锁成功后就会开启一个后台线
程检查加锁的情况，这样解决了第一个问题。

以上方案都是基于单机部署，集群下使用redlock方案。


# getset
127.0.0.1:6379> getset key1 "hello"
(nil)
127.0.0.1:6379> getset key1 "world"
"hello"
```

**复杂数据形式**

```shell
# json
127.0.0.1:6379[3]> SET obj '{"user":1,"name":"jack"}'
OK
127.0.0.1:6379[3]> get obj
"{\"user\":1,\"name\":\"jack\"}"
# 结构化数据 user:{id}:{filed}
127.0.0.1:6379[3]> set user:1:name jack
OK
127.0.0.1:6379[3]> set user:1:age 21
OK
127.0.0.1:6379[3]> get user:1:age
"21"
127.0.0.1:6379[3]> get user:1:name
"jack"
```

