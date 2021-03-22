## Redis 第八课 《事务机制》

**Redis 单条命令可以保证原子性，但是事务无法保证**

> redis事务是一组命令的集合，按照顺序串行化执行。
>
> redis事务没有隔离级别。
>
> 运行中命令错误，不会回滚，其他命令继续执行。

```shell
# 开启事务
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> ZADD k1 12 v2
QUEUED
127.0.0.1:6379> SADD k3 v3
QUEUED
127.0.0.1:6379> HSET map k1 k1
QUEUED
127.0.0.1:6379> LPUSH list 12
QUEUED
# 运行事务
127.0.0.1:6379> EXEC
1) OK
# 运行时异常
2) (error) WRONGTYPE Operation against a key holding the wrong kind of value
3) (integer) 1
4) (integer) 1
5) (integer) 1
# 不影响其他命令执行
127.0.0.1:6379> keys *
1) "map"
2) "k3"
3) "list"
4) "k1"
# 结束事务
127.0.0.1:6379> DISCARD
```

**watch实现乐观锁**

```shell
1. exec discard执行自动解锁
2. unwatch 解锁
# 乐观锁使用
127.0.0.1:6379> set money 100
OK
127.0.0.1:6379> watch money
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> INCRBY money 10
QUEUED
# 其他客户端对money 进行操作 执行失败
127.0.0.1:6379> exec
(nil)
```

