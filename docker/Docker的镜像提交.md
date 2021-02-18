## Docker镜像制作

**镜像**   

> 镜像是一个独立运行的软件包，包括运行环境，配置文件，库，代码，配置变量。

**镜像提交** 

> docker commit -m="message" -a="auther"  容器id  新名称:tag
>
> 生成新镜像，docker images

bug****

> **docker run -it  -p 8088:8080 040bdb29ab37 /bin/bash**   
>
> CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                            NAMES
> 63c297925821   040bdb29ab37   **"/bin/bash"**              2 minutes ago   Up 2 minutes   0.0.0.0:8088->8080/tcp                           beautiful_kirch
> f38f32b69964   040bdb29ab37   **"catalina.sh run"**        2 hours ago     Up 2 hours     0.0.0.0:8080->8080/tcp                           tomcat9
>
> 原因默认的`CMD`参数被`/bin/bash`给代替了，需要自己手动启动。