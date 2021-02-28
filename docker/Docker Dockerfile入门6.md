## Dockerfile

介绍：

1. 编写dockerfile文件
2. docker build 构建镜像
3. docker run 运行镜像
4. docker push 发布到仓库（dockerHub, 阿里云）

### Dockerfile命令

 每一行指令都是一个新的镜像层，最上层是运行的可写容器container. 

 开发必学，企业交付新标准。

>FROM   # 基础镜像 contos
>
>MAINTAINER  # 维护者姓名 + 邮箱
>
>RUN    # 构建时运行的命令
>
>ADD     #  步骤，tomcat, jdk , 添加内容
>
>WORKDIR      # 镜像工作目录
>
>VOLUME       # 挂载的目录
>
>EXPOST          #  暴露的端口配置
>
>CMD              #   **指定容器启动时运行的命令, dockerfile中可以有多条cmd命令，但只是最后一条有效。可被替代**
>
>ENTRYPOINT     #  **指定容器启动时运行的命令, 可追加命令**
>
>ONBUILD           #  ONBUILD 是一个特殊的指令，它后面跟的是其它指令，比如 RUN, COPY 等，而这些指令，在当前镜像构建时并不会被执行。只有当以当前镜像为基础镜像，去构建下一级镜像的时候才会被执行。
>
>Dockerfile 中的其它指令都是为了定制当前镜像而准备的，唯有 ONBUILD 是为了帮助别人定制自己而准备的。
>
>COPY    #  类似ADD ， 将我们文件拷贝到镜像 tar
>
>ENV    # 设置环境变量 

### 实战centos

> 创建自己的centos

```sh
# scratch 代表一个虚拟的镜像，底层地基的概念。
FROM centos
MAINTAINER zhang<1059976480@qq.com>

ENV MYPATH /user/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "-------end--------"
CMD /bin/bash

$ docker build -f dockerfile-centos -t mycentos:v-1 .
# build 最后的点 .指的是镜像构建时打包上传到Docker引擎中的文件目录。
$ docker history 66d90a86b889  查看构建历史
```

> Dockerfile 制作tomcat

```shell
TODO
```

> 发布镜像

```sh
$ docker login -u name
$ docker push 	仓库名/镜像:tag

# 阿里云开启容器镜像服务，开启命名空间，镜像仓库
参考官方文档

docker save / load
$ docker save -o nginx1.18.tar  nginx:1.18
$ docker load -i nginx1.18.tar
```

