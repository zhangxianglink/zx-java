##  

## 常用命令

### 1. JDK[ 命令](https://blog.csdn.net/qq_31156277/article/details/80035236)

```sh
首先是 jps （JVM Process Status Tool）列举正在运行的进程
jps -l 输出类的全名，如果是jar 输出路径
jps -v 输出jvm 	启动的参数

Jstat (JVM Statistics 统计 Monitoring 监控 Tool)
jstat [-命令选项] [vmid] [间隔时间/毫秒] [查询次数]
-class	监视类装载、卸载数量，以及总空间和装载耗时等
-gc	监视堆中 eden、 survivor 、老年代、元空间等空间大小和已使用情况，GC次数、耗时等
-gcmetacapacity	元空间
-gcutil	与gc 类似，但是注重的是占比情况
-printcompilation	输出已经被JIT重新编译的方法
-gcoldcapacity	老年代统计信息
-gcnew	新生代
-gccause	与-gcutil相似，但是会输出上一次GC的原因
```



### 2. Linux常用命令

```sh
1. 查询进程
ps -ef | grep xxxx
ps -aux | grep xxxx
2. 查询进程并杀掉
ps -ef | grep flask | grep -v grep | awk '{print $2}' | xargs kill -9
awk '{print $2}'  ：提取找到的进程行记录中第二列的参数，也就是的进程号
3. 查询占用端口的进程
lsof -i:{port}
netstat -tunlp|grep {port}
4. 根据进程pid查端口
lsof -i | grep pid
netstat -nap | grep pid
5. 根据进程查看工作目录
pwdx pid
```



