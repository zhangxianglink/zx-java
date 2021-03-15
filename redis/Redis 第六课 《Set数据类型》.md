## Redis 第六课 《Set数据类型》

**常规操作**

```shell
# 插入
127.0.0.1:6379> sadd set a b c d e
(integer) 5
# 查询数量
127.0.0.1:6379> scard set
(integer) 5
# 展示数据
127.0.0.1:6379> SMEMBERS set
1) "a"
2) "d"
3) "c"
4) "b"
5) "e"
# 判断数据是否存在
127.0.0.1:6379> SISMEMBER set a
(integer) 1
127.0.0.1:6379> SISMEMBER set f
(integer) 0
# 获取随机数据
127.0.0.1:6379> SRANDMEMBER set 2
1) "a"
2) "e"
127.0.0.1:6379> SRANDMEMBER set 2
1) "d"
2) "b"
# 后进先出
127.0.0.1:6379> spop set 2
1) "b"
2) "e"
# 删除数据
127.0.0.1:6379> srem set c a
(integer) 2
127.0.0.1:6379> srem set  g
(integer) 0
```

**聚合操作**

```shell
# 初始化数据
127.0.0.1:6379> sadd set1 a b c d e f
(integer) 6
127.0.0.1:6379> sadd set2 c b t s f j
(integer) 6
# 获取set1 独有的数据，差集
127.0.0.1:6379> SDIFF set1 set2
1) "a"
2) "d"
3) "e"
# 获取共有数据，交集
127.0.0.1:6379> SINTER set1 set2
1) "c"
2) "b"
3) "f"
# 所有数据，并集
127.0.0.1:6379> SUNION set2 set1
1) "c"
2) "f"
3) "s"
4) "a"
5) "d"
6) "t"
7) "j"
8) "b"
9) "e"
```

**可以用与共同好友，进行关系分组**

