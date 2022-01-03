## Synchronized 同步

### 对象🔒:

```java
1. 用在非静态方法上:
   public synchronized void method(){}
2. 使用在方法内的代码块:
   Object lock = new Object();
   public void run() {
      synchronized (this) {}
      synchronized (lock) {}
    }
```

### 类🔒:

```java
1.使用在静态方法上
  public static synchronized void method(){}
2.使用在代码块
  synchronized(MySyncDemo2.class) {}
```

### 区别:

```java
两种使用方式的区别主要体现在锁住的对象.
对象锁:顾名思义,是对初始化后的实例进行加锁, 从上面代码看,this代表的是当前类的一个具体引用.
MySyncDemo2 mySyncDemo1 = new MySyncDemo2();
MySyncDemo2 mySyncDemo2 = new MySyncDemo2();
Thread t1 = new Thread(mySyncDemo1);
Thread t2 = new Thread(mySyncDemo2);
如果按照以上去执行那么, t1里面锁住的是引用mySyncDemo1, t2里面锁住的是引用mySyncDemo2,这种情况下
不会出现我们期望的串行操作, 因为两个线程不需要去等待.
类锁: 当它用于static的时候, 就代表它不会涉及到具体的对象, 而.class方式更是证明了
  类锁是针对这个类本身的, 上面的代码在执行中回去竞争MySyncDemo2.class.
```

### Synchronized的特征以及缺点:

```java
特征:
1.可重入 (体现在加锁的方法里面,还能使用当前锁)
2.不可中断 (除非结束,或者异常才能退出)
缺点: 
1. 应用场景太少,不灵活.
2. 无法设置超时时间,容易阻塞产生死锁
3. 不可中断
4. debug无法知道是否拿到锁对象
```

### Synchronized使用建议:

```java
1. 尽量不要嵌套
synchronized (this) {
  ....x
  synchronized (lock) { 
    ....x}
}
2. 缩小使用范围
```



