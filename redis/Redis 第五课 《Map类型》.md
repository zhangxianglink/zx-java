## Redis 第五课 《Map类型》

```shell
# 单个插入
127.0.0.1:6379> hset map k1 v1
(integer) 1
127.0.0.1:6379> hset map k2 v2
(integer) 1
127.0.0.1:6379> hset map k3 v3
(integer) 1
# 多个插入
127.0.0.1:6379> hmset map  k4 v4 k5 v5
OK
# 获取
127.0.0.1:6379> HSTRLEN map k1
(integer) 2

127.0.0.1:6379> hget map k1
"v1"

127.0.0.1:6379> hmget map k1 k2 k3
1) "v1"
2) "v2"
3) "v3"

127.0.0.1:6379> hgetall map
 1) "k1"
 2) "v1"
 3) "k2"
 4) "v2"
 5) "k3"
 6) "v3"
 7) "k4"
 8) "v4"
 9) "k5"
10) "v5"
127.0.0.1:6379> hlen map
(integer) 5

127.0.0.1:6379> hkeys map
1) "k1"
2) "k2"
3) "k3"
4) "k4"
5) "k5"

127.0.0.1:6379> hvals map
1) "v1"
2) "v2"
3) "v3"
4) "v4"
5) "v5"
# 存在1, 不在0
127.0.0.1:6379> HEXISTS map k1
(integer) 1
127.0.0.1:6379> HEXISTS map k10
(integer) 0

# 删除，不存在0 ，存在>0
127.0.0.1:6379> hdel map k4 k5
(integer) 2
127.0.0.1:6379> hkeys map
1) "k1"
2) "k2"
3) "k3"
127.0.0.1:6379> hdel map k7
(integer) 0 
```

**特殊**

```shell
# key已经存在，不可改变
127.0.0.1:6379> HSETNX usre id 0
(integer) 1
127.0.0.1:6379> HSETNX usre id 22
(integer) 0
# 步长
127.0.0.1:6379> hset user age 0
(integer) 1
127.0.0.1:6379> HINCRBY user age 10
(integer) 10
127.0.0.1:6379> HINCRBY user age -100
(integer) -90
```

**map适合设置一些复杂对象数据**

