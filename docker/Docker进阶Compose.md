## Docker Compose 

> 以YAML文件的形式，批量容器编排。假如公司项目需要多个服务，redis, mysql,
>
> 微服务3部分，这样的话就有5个服务需要启动，然后启动顺序也可能会有规律，人工
>
> 启动不安全，多台机器的话，工作量大。不如compose一条命令部署。

**使用流程：**

1. 编写通用的Dockerfile
2. 编写管理容器服务的docker-compose.yml
3. Docker compose命令运行.

[基本使用](https://docs.docker.com/compose/gettingstarted/)

[yml规则](https://docs.docker.com/compose/compose-file/compose-file-v3/#volumes-for-services-swarms-and-stack-files)

       -- 第一层 version
       -- 第二层 service
       -- 第三层 网络，卷，等全局配置
**使用案例**

```shell
# Dockerfile
FROM java:8
COPY *.jar /app.jar
EXPOSE 8082
CMD ["--server.port=8082"]
ENTRYPOINT ["java","-jar","/app.jar"]
# docker-compose.yml
services:
  demoapp:
    build: .
    image: demoapp
    depends_on:
      - redis
    ports:
      - "8082:8082"
  redis:
    image: "redis:alpine"
# docker-compose up / down
```





