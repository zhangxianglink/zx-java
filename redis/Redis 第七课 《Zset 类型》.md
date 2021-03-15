## Redis 第七课 《Set数据类型》

**排序**

```shell
# ZSET 的特点应该就是排序了，能够做top100, 权重信息排列。
127.0.0.1:6379> zadd top10 1 "one" 2 'tow' 3 "three"
(integer) 3
127.0.0.1:6379> zadd top10 4 "four" 5 'five' 6 "six"
(integer) 3
127.0.0.1:6379> zadd top10 7 "seven" 8 'eight' 9 "nine" 10 "ten"
(integer) 4

# 根据分值排序，正序
127.0.0.1:6379> ZRANGEBYSCORE top10 (0 3
1) "one"
2) "tow"
3) "three"
127.0.0.1:6379> ZRANGEBYSCORE top10 -inf +inf
 1) "one"
 2) "tow"
 3) "three"
 4) "four"
 5) "five"
 6) "six"
 7) "seven"
 8) "eight"
 9) "nine"
10) "ten"

# 根据分值排序，倒序排列
127.0.0.1:6379> ZREVRANGEBYSCORE top10 +inf -inf
 1) "ten"
 2) "nine"
 3) "eight"
 4) "seven"
 5) "six"
 6) "five"
 7) "four"
 8) "three"
 9) "tow"
10) "one"
127.0.0.1:6379> ZREVRANGEBYSCORE top10 (11 (5 limit 0 2
1) "ten"
2) "nine"
```

**东西蛮多的，以后再写**

