## Redis 第二课 《常用命令》

 redis 默认单线程，6以后支持多线程。
 redis 所有的数据都会放到内存中，单线程无需上下文切换，多次读写使用一个CPU。

```shell
# 打开redis.conf 
databases 16 代表一共有16个数据库，我们默认是第0个
# 切换数据库
$ select 2
# 数据大小
$ DBSIZE
# 所有的键
$ keys *
# 清空当前库
$ flushdb
# 清空所有的库
$ flushall
# 删除
$ DEL
# 判断是否存在
$ EXISTS
# 有效期
$ EXPIRE
# 查看剩余有效期
$ TTL
```

