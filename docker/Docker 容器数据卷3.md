## Docker 容器数据卷

**为防止容器删除数据丢失，所以需要持久化数据。**

Docker 容器中数据同步保存到本地**采用卷技术**：实际上就是文件夹映射，挂载外部存储空间。***容器之间也可以数据共享。***

<hr/>

### 数据卷的使用

> docker run -it -v  主机目录:容器目录
>
> 1.  docker run --name centos01 -it -v /home/centos01:/home centos /bin/bash
> 2. 在主机，容器目录分别 touch 新文件
> 3. docker inspect centos01 
>
> ```shell
>     "Mounts": [
>         {
>             "Type": "bind",
>             "Source": "/home/centos01",  主机目录
>             "Destination": "/home", 容器目录
>             "Mode": "",
>             "RW": true,
>             "Propagation": "rprivate"
>         }
>     ],
> ```
> 

### 数据卷使用-mysql

> 1. docker images
>
> 2. docker pull mysql:5.7
>
> 3.  docker run -d -p 3306:3306 \
>
>    -e MYSQL_ROOT_PASSWORD=123456 \
>    -v /home/mysql/conf:/etc/mysql/conf.d \
>    -v /home/mysql/data:/var/lib/mysql \
>    --name mysql01 mysql:5.7
>
> 4. 外网连接测试。
>
> 5. 对比本地，容器目录的内容。
>
> 6. 不删除镜像，数据存在两份；删除镜像，本机数据还在。



