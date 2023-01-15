## Redis 第七课 《Set数据类型》

**排序**

```shell
# ZSET 的特点应该就是排序了，能够做top100, 权重信息排列。
> ZADD stus 85 jack 89 lucy 82 rose 95 tom 78 Jerry 92 amy 76 miles
7
# 删除tom
> ZREM stus tom
1
# 获取个数
> zcard stus
6
# 获取分数
> ZSCORE stus jack
85
# 获取排名， ZRANK （ascend） ZREVRANK (descend) 从0开始计数
> ZRANK stus amy
5
> ZREVRANK stus amy
0
# 80分及以下人数
> ZCOUNT stus 0 80
2
# 自增操作
> ZINCRBY stus 10 rose
92
# 根据分值排序
> ZRANGE stus 0 -1
miles
Jerry
jack
lucy
amy
rose
> ZREVRANGE stus 0 -1
rose
amy
lucy
jack
Jerry
miles
# 按分值查信息
> ZRANGEBYSCORE stus 0 80
miles
Jerry
```


### 聚合操作类似SET
