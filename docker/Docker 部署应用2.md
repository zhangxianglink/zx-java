## Docker 部署nginx

```shell
1. docker pull nginx:1.18.0 下载想要的版本
2. docker run --name nginx02 -d -p 9090:80 b9e1dc12387a
3. 打开安全组或者防火墙
4. 进入 docker exec -it nginx02 /bin/bash
5. 查找  whereis nginx
6. 修改  vim /etc/nginx/nginx.conf
```



## Docker 部署tomcat

```shell
1. docker pull tomcat:9.0
2. docker run -d --name tomcat9 -p 8080:8080 040bdb29ab37
3. docker ps 
4. docker exec -it 0748544f9737 /bin/bash
5. cp -r webapps.dist/* webapps/ 添加展示界面
为啥docker一个tomcat镜像这么大？
```



## Docker 部署es + kibana

```shell
1.docker pull elasticsearch:7.6.2
2.docker pull kibana:7.6.2
3.docker run -d --name elasticsearch01 --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node"  -e ES_JAVA_OPTS="-Xms64m -Xmx512m"  f29a1ee41030
4. docker inspect elasticsearch01 查看ip
5. docker run --name mykibana -e ELASTICSEARCH_HOSTS=http://172.17.0.4:9200 -p 5601:5601 -d f70986bc5191
6. 一开始多次kibana 启动失败， 通过exec 进入容器内部 http://172.17.0.4:9200, 删除容器，重建多次成功。
```

[参考链接，异常解决思路](https://blog.csdn.net/qq_33178228/article/details/108196889)

[文件方式配置](https://www.cnblogs.com/wwjj4811/p/13832022.html)