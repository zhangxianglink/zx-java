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
# 尾部添加数据
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

**计数器**

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
TODO
# getset cas
```

**复杂数据形式**

```shell
# json

# 结构化数据 user:{id}:{filed}
```

