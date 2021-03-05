## Docker 网络

> 查看docker网络

```shell
$ docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
a346cc35207f   bridge    bridge    local  # 桥接网络（默认）
104e914d1a83   host      host      local  # 宿主机（Linux服务器共享网络）
777c296edc22   none      null      local  # 不配置网络
               container                  # 容器互联
```

> docker创建网络

```shell
 $ ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo # 本地IP（用于和本地内部通信）
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 00:16:3e:1e:a7:1a brd ff:ff:ff:ff:ff:ff
    inet 172.16.250.10/20 brd 172.16.255.255 scope global dynamic eth0  # 阿里云内网ip（用于和其他主机通信）
       valid_lft 313690696sec preferred_lft 313690696sec
3: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default
    link/ether 02:42:b9:28:29:c5 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.1/16 brd 172.17.255.255 scope global docker0 # docker网桥（容器通信的网桥，类似路由器）
       valid_lft forever preferred_lft forever
```

> Docker 自定义网络 

```shell
# 平时启动容器
$ docker run -d -P --name tomcat1 tomcat
# 补全默认网络
$ docker run -d -P --name tomcat1 --net bridge tomcat

# docker0网络。默认使用，域名无法访问。所以我们自定义
$ docker network create --driver bridge --subnet 10.186.0.0/16 --gateway 10.186.0.1 mynet
$  docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
a346cc35207f   bridge    bridge    local
104e914d1a83   host      host      local
40f97793930f   mynet     bridge    local
777c296edc22   none      null      local
# 启动容器
$ docker run -d -P --name tomcat-net1 --net mynet tomcat
$ docker run -d -P --name tomcat-net1 --net mynet tomcat
# 检查网络
$ docker network inspect mynet
"Containers": {
            "6b3c201dbcd8a41968d28d5906feb504b159e43547421c704f27859b50005cbc": {
                "Name": "tomcat-net2",
                "EndpointID": "e672ea127cec1c06125c2f50ee329ebe4a9e183bf410d152d1b690b18c938207",
                "MacAddress": "02:42:0a:ba:00:03",
                "IPv4Address": "10.186.0.3/16",  # tomcat-net2
                "IPv6Address": ""
            },
            "845861bd6f1ba40e17e68b7b500ad3231cb5cfa59d21ebec6804198fa125420e": {
                "Name": "tomcat-net1",
                "EndpointID": "eb0875a2b4155097427df64b01faf279af753c184efa11a3fccf85248e684f2e",
                "MacAddress": "02:42:0a:ba:00:02",
                "IPv4Address": "10.186.0.2/16", # tomcat-net1
                "IPv6Address": ""
# 容器之间相互请求(无需--link 在hosts文件添加映射，自带DNS SERVER)
$ docker exec -it tomcat-net1 ping tomcat-net2
$ docker exec -it tomcat-net2 ping 10.186.0.2
```

> 网络互联（外部容器访问自定义网络）

```shell
$ docker network --help
connect     Connect a container to a network  # connect [网络] [容器]
$ docker network connect mynet tomcat-docker
$ docker inspect tomcat-docker
# mynet 网络多了tomcat-docker的信息，这样tomcat-docker2个IP地址（docker0,mynet）。
 "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "a346cc35207f05747ad9ff8b9eba6d9418cf44c8d332338ebf17923061914657",
                    "EndpointID": "9ead7645b956c6be58c4e6a25ce3c3b37626c69f4441cceb61d8c48ef8e8af95",
                    "Gateway": "172.17.0.1",
                    "IPAddress": "172.17.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:11:00:02",
                    "DriverOpts": null
                },
                "mynet": {
                    "IPAMConfig": {},
                    "Links": null,
                    "Aliases": [
                        "2cdf4c196b66"
                    ],
                    "NetworkID": "40f97793930fcf48accef50e03269e785f83a35ca3aab0fc49560a2713747a1e",
                    "EndpointID": "52cf195e7e58c81cbd7f14f6bb75e0373f6a92343611521a762eb9c1804dab91",
                    "Gateway": "10.186.0.1",
                    "IPAddress": "10.186.0.4",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:0a:ba:00:04",
                    "DriverOpts": {}
                }
            }
 # 测试链接
 $  docker exec -it tomcat-net1 ping tomcat-docker
 $  docker exec -it tomcat-docker ping tomcat-net2
```

