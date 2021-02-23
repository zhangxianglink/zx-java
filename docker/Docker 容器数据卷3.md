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
> 3. ```shell
>    docker run -d -p 3306:3306 \
>    
>    -e MYSQL_ROOT_PASSWORD=123456 \
>    -v /home/mysql/conf:/etc/mysql/conf.d \
>    -v /home/mysql/data:/var/lib/mysql \
>    --name mysql01 mysql:5.7
>    ```
>
>    
>
> 4. 外网连接测试。
>
> 5. 对比本地，容器目录的内容。
>
> 6. 不删除镜像，数据存在两份；删除镜像，本机数据还在。



### 匿名挂载

> 1. docker run -d --name niming -p 9090:80 /etc/nginx nginx:1.18
>
> 2.  docker volums ls
>
>    **DRIVER    VOLUME NAME**
>    local     63a038dc41007b0ed569f45f6fd1fdf50a8977cf8237e612e2703bee465c4b87
>
> 3. ```shell
>    docker volume inspect 63a038dc41007b0ed569f45f6fd1fdf50a8977cf8237e612e2703bee465c4b87
>    [
>        {
>            "CreatedAt": "2021-02-20T13:31:36+08:00",
>            "Driver": "local",
>            "Labels": null,   下面目录存放从容器目录同步来的数据
>            "Mountpoint": "**/var/lib/docker/volumes/**63a038dc41007b0ed569f45f6fd1fdf50a8977cf8237e612e2703bee465c4b87/_data",
>            "Name": "63a038dc41007b0ed569f45f6fd1fdf50a8977cf8237e612e2703bee465c4b87",
>            "Options": null,
>            "Scope": "local"
>        }
>    ]
>    ```
>    
>    



### 具名挂载

> 1. docker run -P -d -v **juming**:/etc/nginx --name juming_nginx nginx:1.18
>
> 2. docker volume ls
>    **DRIVER    VOLUME NAME**
>    local     63a038dc41007b0ed569f45f6fd1fdf50a8977cf8237e612e2703bee465c4b87
>    local     **juming**
>    
> 3. docker volume inspect juming
> 
> 4. ```shell
>    [
>        {
>            "CreatedAt": "2021-02-20T14:45:38+08:00",
>            "Driver": "local",
>            "Labels": null,
>            "Mountpoint": "**/var/lib/docker/volumes/**juming/_data",
>            "Name": "juming",
>            "Options": null,
>            "Scope": "local"
>        }
>    ]
>    ```
> 



### 挂载总结

> -v 容器内路径				*#匿名挂载* 
>
> -v 卷名:容器内路径			*#具名挂载* 
>
> -v /宿主机路径:容器内路径		*#指定路径挂载*
>
> *#通过 -v 容器内路径：ro rw改变读写权限*
>
>  ro	readonly 	*#只读* rw	
>
> readwrite	*#可读可写* 
>
> 一旦这个设置了容器权限，容器对我们挂载出来的内容就有限定了
>
>  docker run -d -P -name nginx002 -v juming-nginx:/etc/nginx:ro nginx 
>
> docker run -d -P -name nginx002 -v juming-nginx:/etc/nginx:rw nginx
>
>  *ro 就说明这个路径只能宿主机来操作，容器内部是无法操作的* (***多数据库挂载一个数据卷，不同数据库容器之间读写分离***)
>
> **PS(删除容器，挂载内容依旧存在)**