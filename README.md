# 全局

### 用户系统

1.登录之后换一个token回来，每次请求数据带上token，其他人利用同一账号登录之后token会被改变(每次登录都会重置)，所以上个用户就没法使用了。 

2.服务端拿到客户端的token，说明客户端认为登录了，就先校验token，如果校验失败，直接返回登录状态错误。 同理客户端如果拿到登录错误态，应该先清空本地登录信息，然后跳到登录页面。 

3.服务端token存缓存，而不是数据库，查数据库费时。 

### 分类系统 

1.TODO:请求分类添加接口，需要带上超级管理员账户(token或者直接用户名加密码)。服务端校验成功后才可以添加分类 

2.TODO:商家上传商品信息可以选择分类 

# Server

### 数据库编码

1. 保证数据库编码
   1. use某个数据库之后再查看。因为mysql可以为每个数据库指定不同的编码
   2. 创建数据库时即指定编码
      - `CREATE DATABASE mall DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;`
      - 亦可通过修改配置文件、修改数据库内容等方式修改，详情参考https://blog.csdn.net/u012410733/article/details/61619656
   3. 使用`show variables like '%char%';`校验
   4. 用命令插入、查询校验中文问题是否解决
   5. 首先要保证命令窗口的文本编辑是否使用了utf-8
2. 保证代码层面的编码
   1. ide的文本是否使用了utf-8
3. 数据库连接使用utf-8
   1. `jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf-8`

### 端口占用

- mac
  - sudo lsof -i :8080 查看端口占用进程
  - sudo kill -9 81881 杀进程                                                                                            

### 注解

- RequestBody
  - 前端请求必须Content-type：application/json。body中通过raw传入json

### 注册登录

- userId+时间戳 进行md5产生token

# Client

