## goim 高性能分布式消息广播服务
该项目为之前IM服务器，去掉好友、群组等社交业务后重新发布的轻量级版本，该项目标为打造通用轻量级消息广播服务器。底层基于云风大神的Skynet框架，有兴趣的同学自行研究。

## 运行环境搭建
1.拉取代码
```sh
$ git clone git@github.com:lemontree2015/goim.git
```

2.修改配制.conf（monitor_server、chat_restapi、chat_room、chat_server、chat_session下.conf文件）

3.服务启动

monitor_server服务注册与发现
```sh
$ ./monitor_server --conf_path=/配制文件路径
```

chat_session维护长链接Session映射关系
```sh
$ ./chat_session --conf_path=/配制文件路径
```

chat_server客户端长链接服务
```sh
$ ./chat_server --conf_path=/配制文件路径
```

chat_room聊天室
```sh
$ ./chat_room --conf_path=/配制文件路径
```

chat_restapi HTTP Restapi接口
```sh
$ ./chat_restapi --conf_path=/配制文件路径
```

## 项目文档持续更新中，使用中如有疑问联系微信备注:github
![image](http://img1.sscmgroup.com/avatar/wx.jpg)

## 项目规划

目前发布版本支持消息协议如下：

-  1.登陆认证（单点登陆，先登陆账号收到KickOff广播，长链接通道关闭）
-  2.用户长链接心跳
-  3.进入聊天室
-  4.聊天室心跳（可选）
-  5.消息广播（一对一、房间内广播、全局广播）
-  6.后续功能及文档持续更新，敬请期待。。。。