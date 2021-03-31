## Redis 命令注意事项

1. 过期时间意外丢失。

   ```shell
   127.0.0.1:6379> set name 'bake' ex 60
   OK
   127.0.0.1:6379> ttl name
   (integer) 51
   # 修改 key 的只使用 SET 命令，而没有加上「过期时间」的参数，那这个 key 的过期时间将会被「擦除」
   127.0.0.1:6379> set name 'jack'
   OK
   127.0.0.1:6379> ttl name
   (integer) -1
   ```

2. Del 阻塞Redis

   ```she
   key 是 String 类型，DEL 时间复杂度是 O(1)
   key 是 List/Hash/Set/ZSet 类型，DEL 时间复杂度是 O(M)，M 为元素数量
   如果你要删除的是一个非 String 类型的 key，这个 key 的元素越多，那么在执行 DEL 时耗时就越久！
   
   处理方案：
   查询元素数量：执行 LLEN/HLEN/SCARD/ZCARD 命令
   判断元素数量：如果元素数量较少，可直接执行 DEL 删除，否则分批删除（例如每次500）
   hash key: hscan -> hel 
   set key: sscan -> srem
   list key : ltrim 
   sorted set key: zremrangebyrank
   ```

3. **RANDOMKEY 竟然也会阻塞 Redis**

   ```she
   1.master 随机取出一个 key，判断是否已过期
   2.如果 key 已过期，删除它，继续随机取 key
   3.以此循环往复，直到找到一个不过期的 key，返回
   但这里就有一个问题了：如果此时 Redis 中，有大量 key 已经过期，但还未来得及被清理掉，那这个循环就会持续很久才能结束，而且，这个耗时都花费在了清理过期 key + 寻找不过期 key 上。
   ```

4. MONITOR导致OOM

   ```she
   127.0.0.1:6379> MONITOR # 打印其他客户端的命令
   OK
   1617088496.578258 [0 127.0.0.1:39484] "COMMAND"
   1617088506.751815 [0 127.0.0.1:39484] "get" "name"
   1617088523.867372 [0 127.0.0.1:39484] "set" "name" "kk" "ex" "90"
   1617088527.104198 [0 127.0.0.1:39484] "ttl" "kk"
   1617088532.764742 [0 127.0.0.1:39484] "ttl" "name"
   如果你的 Redis QPS 很高，这将会导致这个输出缓冲区内存持续增长，占用 Redis 大量的内存资源，如果恰好你的机器的内存资源不足，那 Redis 实例就会面临被 OOM 的风险
   ```

   

