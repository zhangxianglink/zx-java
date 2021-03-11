## Redis 第一课《本地单机安装》

```shell
# 安装文件
$ wget http://download.redis.io/releases/redis-6.0.6.tar.gz
$ tar xzf redis-6.0.6.tar.gz
$ cd redis-6.0.6
# redis	是使用c语言开发，先配置gcc 环境
$ gcc -v
# 版本低于5.3，执行先命令
$ yum -y install centos-release-scl && yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils && scl enable devtoolset-9 bash
# 编译redis
$ make
$ make install
提示"Hint: It's a good idea to run 'make test' ;)"，代表安装成功.
也可以进入/usr/local/bin 目录下查看
# 进入编译好的项目, 备份redis.conf,修改（守护进程）daemonize yes
$ src/redis-server /usr/local/bin/my-redis-config/redis.conf (自行备份文件，原文件修改也可)
# 进入命令行
$ src/redis-cli -p 6379
127.0.0.1:6379> set goo jacklove
OK
127.0.0.1:6379> get goo
"jacklove"
# 性能测试
$ redis-benchmark -n 10000 -c 100
10000 requests completed in 0.12 seconds  # 10000 请求
  100 parallel clients  # 100 并发连接
  3 bytes payload       # 每次请求3字节
  keep alive: 1         # 单机


0.01% <= 0.2 milliseconds
99.38% <= 2 milliseconds
100.00% <= 2 milliseconds
80645.16 requests per second  # 每秒性能
```

