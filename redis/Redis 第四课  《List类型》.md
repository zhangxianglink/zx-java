## Redis 第四课  《List类型》

可以使用List底层是一个双端链表。可以用它实现消息队列，消息栈。

**首尾元素操作**

```shell
127.0.0.1:6379> Lpush list one two three four five
(integer) 5
127.0.0.1:6379> Lrange list 0 -1
1) "five" 2) "four" 3) "three" 4) "two" 5) "one"
127.0.0.1:6379> LPOP list
"five"
从头插入数据，从头推出，后进先出具有栈的特性

127.0.0.1:6379> lpush list "one" "two" "three"
(integer) 3
127.0.0.1:6379> lrange list 0 -1
1) "three" 2) "two" 3) "one"
127.0.0.1:6379> Rpush list zero
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "three" 2) "two" 3) "one" 4) "zero" 
127.0.0.1:6379> rpop list
"zero"
List 也可以进行尾部操作，可以作为双向队列使用。
```

**查询**

```shell
127.0.0.1:6379> LLen list
(integer) 3
127.0.0.1:6379> Lindex list 2
"one"
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
```

**删除集合元素**

```shell
# 删除------------------------------------
127.0.0.1:6379> lpush list a b c b d c e
(integer) 7
# count 为 0 时删除所有符合条件的数据
127.0.0.1:6379> lrem list 0 b
(integer) 2
# count代表删除的数量， 为负值时从尾部开始删除，正值反之
127.0.0.1:6379> lpush list a b c b d c e
(integer) 7
127.0.0.1:6379> lrem list -1 c
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "e"
2) "c"
3) "d"
4) "b"
5) "b"
6) "a"
```

**截取**

```shell
# 截取需要的部分, 保留到原集合上
127.0.0.1:6379> lrange list 0 -1
1) "e"
2) "c"
3) "d"
4) "b"
5) "b"
6) "a"
127.0.0.1:6379> ltrim list 1 3
OK
127.0.0.1:6379> lrange list 0 -1
1) "c"
2) "d"
3) "b"
# 推出尾部元素进入另一个集合
127.0.0.1:6379> RPOPLPUSH list otherlist
"b"
127.0.0.1:6379> lrange otherlist 0 -1
1) "b"
127.0.0.1:6379> RPOPLPUSH list otherlist
"d"
127.0.0.1:6379> lrange otherlist 0 -1
1) "d"
2) "b"
```

**修改元素**

```shell
# 修改指定下标的元素
127.0.0.1:6379> lpush list a b c d e f g
(integer) 7
127.0.0.1:6379> lset list 1 value
OK
127.0.0.1:6379> lrange list 0 -1
1) "g"
2) "value"
3) "e"
4) "d"
5) "c"
6) "b"
7) "a"
# 插入指定元素前后
127.0.0.1:6379> LINSERT list before c before
(integer) 8
127.0.0.1:6379> lrange list 0 -1
1) "g"
2) "value"
3) "e"
4) "d"
5) "before"
6) "c"
7) "b"
8) "a"
127.0.0.1:6379> LINSERT list after c after
(integer) 9
127.0.0.1:6379> lrange list 0 -1
1) "g"
2) "value"
3) "e"
4) "d"
5) "before"
6) "c"
7) "after"
8) "b"
9) "a"
```



