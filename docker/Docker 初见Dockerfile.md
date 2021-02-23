## Dockerfile 简单编写

Dockerfile就是镜像的构造文件，命令脚本。

```sh
FROM centos

VOLUME ["volume01","volume02"] #镜像挂载

CMD echo "--------end--------"
CMD /bin/bash
```

1. docker build -f dockerfile1  -t xiang/centos:1.0 .
2. 进入volume01 , touch  1.txt
3. docker images

```sh
REPOSITORY        TAG       IMAGE ID       CREATED          SIZE
xiang/centos      1.0       b714ffffbcf1   11 seconds ago   209MB
```

4. docker volume ls 

5.  多了两个新的匿名挂载路径

##  Docker 容器数据卷

核心 --volumes-from , 多个容器之间共享父容器的数据卷。

启动容器测试：

> 1. 父容器 启动 docker run -it --name docker01 b714ffffbcf1 /bin/bash
> 2. 子容器 docker run -it --name docker02 --volumes-from docker01  b714ffffbcf1 /bin/bash
> 3. 经过测试，本地新增文件，子容器新增文件，在父容器都可以看到
> 4. 再添加子容器，删除父容器，依旧可以分享数据
> 5.  docker inspect 查看发现父子容器**数据卷**挂载目录是同一个，所以同步的是父容器数据卷的物理地址

