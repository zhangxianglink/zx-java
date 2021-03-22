## Redis第九课《配置文件》 

1. 配置文件对大小写不敏感

2. Include 可以包含多个配置文件

3. Bind 指定绑定的IP

4. Protected-mode yes 保护模式

5. port 端口

6. daemonize yes 后台运行

7. Pidfile 进程文件 后台运行配置

8. Loglevel notice 生产环境日志

9. Logfile "" 输出日志文件位置

10. save 持久化规则

11.  after 900 sec (15 min) if at least 1 key changed
     after 300 sec (5 min) if at least 10 keys changed
     after 60 sec if at least 10000 keys changed

12. Stop-write-on-bgsave-error yes 后台保存出错是否停止写操作

13. Rdbcompression yes 是否压缩 rob 文件，需要去消耗CPU资源

14. Rdbchecksum yes 保存rdb文件时候，进行错误检查

15. Dir 持久化文件保存目录

16. replication 主从复制***

17. $ CONFIG GET requirepass 获取密码

18. maxclients 最大客户端限制

19. Maxmemory 最大内存容量

20. Maxmemory-policy noecication 内存满了处理策略

21. 1、volatile-lru：只对设置了过期时间的key进行LRU（默认值） 

    2、allkeys-lru ： 删除lru算法的key  

    3、volatile-random：随机删除即将过期key  

    4、allkeys-random：随机删除  

    5、volatile-ttl ： 删除即将过期的  

    6、noeviction ： 永不过期，返回错误

22. append only。 aof持久化机制

23. Appendfsync    三种模式

24. 设置为always时，会极大消弱Redis的性能，因为这种模式下每次write后都会调用fsync（Linux为调用fdatasync）。

    如果设置为no，则write后不会有fsync调用，由操作系统自动调度刷磁盘，性能是最好的。

    everysec为最多每秒调用一次fsync

**看不懂没关系，打开redis.conf 去阅读注解**

