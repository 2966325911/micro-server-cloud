# micro-server-cloud
# 微服务学习SpringCloud，均参考尚硅谷周阳老师springCloud2020的学习，在B站视频学习

## 一、服务注册于发现
服务注册中心本质上是为了解耦服务提供者和服务消费者
#### CAP理论
一致性(Consistency) (所有节点在同一时间具有相同的数据)
可用性(Availability) (保证每个请求不管成功或者失败都有响应)
分隔容忍(Partition tolerance) (系统中任意信息的丢失或失败不会影响系统的继续运作)

**主流注册中心产品**

|                 | Nacos                      | Eureka      | Consul            | CoreDNS    | Zookeeper  |
| --------------- | -------------------------- | ----------- | ----------------- | ---------- | ---------- |
| 一致性协议      | CP+AP                      | AP          | CP                | -          | CP         |
| 健康检查        | TCP/HTTP/MYSQL/Client Beat | Client Beat | TCP/HTTP/gRPC/Cmd |            | Keep Alive |
| 负载均衡策略    | 权重/metadata/Selector     | Ribbon      | Fabio             | RoundRobin |            |
| 雪崩保护        | 有                         | 有          | 有                | 无         | 无         |
| 自动注销实例    | 支持                       | 支持        | 不支持            | 不支持     | 支持       |
| 访问协议        | HTTP/DNS                   | HTTP        | HTTP/DNS          | DNS        | TCP        |
| 监听支持        | 支持                       | 支持        | 支持              | 不支持     | 支持       |
| 多数据中心      | 支持                       | 支持        | 支持              | 不支持     | 不支持     |
| 跨注册中心同步  | 支持                       | 不支持      | 支持              | 不支持     | 不支持     |
| SpringCloud集成 | 支持                       | 支持        | 支持              | 不支持     | 不支持     |
| Dubbo集成       | 支持                       | 不支持      | 不支持            | 不支持     | 支持       |
| K8S集成         | 支持                       | 不支持      | 支持              | 支持       | 不支持     |


## 二、负载均衡
**1、业务服务集群问题**

业务使用集群，一个服务部署多个，则需要在调用的主程序入口使用负载均衡机制，
以RestTemplate调用服务为例，必须利用@LoadBalanced为其开始负载均衡能力，
否则利用服务名进行接口访问时，会报UnknownHostException
~~~
 "timestamp": "2020-03-22T03:32:01.254+0000",
 "status": 500,
 "error": "Internal Server Error",
 "message": "I/O error on GET request for \"http://CLOUD-PAYMENT-SERVICE/payment/get/5\": 
   CLOUD-PAYMENT-SERVICE; nested exception is java.net.UnknownHostException: CLOUD-PAYMENT-SERVICE",
~~~
eureka2.0 由于已经停止更新，故不再使用

#### Hystrix 断路器  
功能是，当对某个服务的调用在一定的时间内（默认10s，由metrics.rollingStats.timeInMilliseconds配置），有超过一定次数（默认20次，由circuitBreaker.requestVolumeThreshold参数配置）并且失败率超过一定值（默认50%，由circuitBreaker.errorThresholdPercentage配置），该服务的断路器会打开。返回一个由开发者设定的fallback

重要概念：
##### 服务降级
   服务器忙，请稍后重试，不让客户等并立即返回一个友好提示，fallback  
   发生降级的情况：程序异常、超时、服务熔断触发服务降级、线程池/信号量打满也会
   导致服务降级
##### 服务熔断
达到最大服务访问后，直接拒绝访问，然后采用服务降级的方法并返回错误  
熔断机制是应对雪崩效应的一种微服务链路保护机制，当扇出链路的某个服务
出错不可用或者响应时间太长时，会进行服务的降级，进而熔断改节点微服务的调用，
快速返回错误的响应信息。当检测到该节点微服务调用响应正常后，恢复调用链路  
Spring Cloud中，利用hystrix实现，缺省是5秒20次调用失败，就会启动熔断机制，
熔断机制的注解时候@HystrixCommand
##### 服务限流
秒杀高并发等操作，避免瞬时流量达到高峰，避免拥挤，一秒钟N个，有序进行

## 三、SpingCloud Gateway
官网地址：https://cloud.spring.io/spring-cloud-gateway/reference/html/
SpringCloud Gateway是SpringCloud生态系统中的网关，目标是替代zuul，基于webFlux框架实现，
WebFlux框架底层使用了高性能的Reactor模式通信框架Netty ，
SpringCloud Gateway的目标提供统一的路由表方式基于Filter链的方式提供了
网关基本个的功能，例如：安全、监控/指标、限流。
### 特性
动态路由：能够匹配任何请求属性；  
可以对路由指定Predicate（断言）和Filter（过滤器）；  
集成Hystrix的断路器功能；  
集成SpringCloud服务发现功能；  
请求限流功能；  
支持路劲重写；
### 三大核心概念
#### Route（路由）
路由是构建网关的基本模块，由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由
#### Predicate（断言）
开发人员可以匹配Http请求头的所有内容（例如请求头或者请求参数），如果请求与断言匹配则进行路由
#### Filter(过滤)
指spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改  
生命周期：pre和post ，种类GatewayFilter（单一）和GlobalFilter(全局)

Gateway路由的配置方式有两种，在yml文件和代码中均可配置
```$xslt
spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: payment_route # 路由的id,没有规定规则但要求唯一,建议配合服务名
          #匹配后提供服务的路由地址,配置后可以不提供8001的服务访问地址，用9527的访问地址进行访问即可
#          uri: http://localhost:8001
          #利用服务名进行路由
          uri: lb://cloud-payment-service
          predicates:
            - Path=/payment/get/** # 断言，路径相匹配的进行路由
              # 在2020-04-12T12:13:47.485+08:00这个时间之后 /payment/get这个才有效，此处时间用ZonedDateTime去获取
              #- After=2020-04-12T12:13:47.485+08:00[Asia/Shanghai]
              # 在2020-04-12T16:13:47.485+08:00这个时间之前 /payment/get这个才有效
              #- Before=2020-04-12T16:13:47.485+08:00[Asia/Shanghai]
              # 访问过程中带不带cookie进行访问key=username value=zzyy，可以使用curl测试，postman等
              #- Cookie=username,zzyy
              # 访问过程中带不带header ，请求头要有X-Request-Id属性，并且值为正数（加号(\d+)）
              #- Header=X-Request-Id, \d+
              #- Host=**.vic.com
              #- Method=GET
              #- Query=username, \d+ # 要有参数名username并且值还要是正整数才能路由
            # 过滤
            #filters:
            #  - AddRequestHeader=X-Request-red, blue
        - id: payment_route2
#          uri: http://localhost:8001
          #利用服务名进行路由
          uri: lb://cloud-payment-service
          predicates:
            Path=/payment/lb/** #断言,路径相匹配的进行路由
```
代码中注入RouteLocator的Bean
```$xslt
   @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //对guonei进行路由，访问的http://localhost:9527/guonei 路由到 http://news.baidu.com/guonei
        return builder
                .routes()
                .route("path_route_vic", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
    }
```
application.yml是用户级别的资源配置项  
bootstrap.yml是系统级别的，加载优先级更高

### 四、SpringCloud Config配置中心客户端配置

server配置，application.yml
```$xslt
spring:
  application:
    name: cloud-config-center
  cloud:
    config:
      server:
        git:
          # 跳过ssl认证
          skipSslValidation: true
          # git配置中心地址
          uri: https://github.com/xxxxxx/springcloud-config.git
          search-paths:
            - springcloud-config
      #读取分支名称
      label: master
```
client配置，bootstrap.yml
```$xslt

spring:
  application:
    name: config-client
  cloud:
    config:
      label: master # 分支名称
      name: config #配置文件名称
      profile: dev # 读取的后缀，上述三个综合，为master分支上的config-dev.yml的配置文件被读取，http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心的地址,config的server端


eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka

#暴露监控端点，改变配置文件的yml时便于刷新
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
还需在controller层配置@RefreshScope，完成配置后要想通过client端访问
，还需动态刷新，需要发送请求post请求curl -X POST "http://localhost:3355/actuator/refresh"刷新后，再次调用即可刷新，
如果有多个客户端，则每个客户端都需要发送一次这样的请求，采用消息总线，则只需要在配置中心服务端发送一次，
这样所有的客户端都可以收到该消息，进行自动刷新，不用手动去刷新其他客户端。

### 五、SpringCloud Bus 消息总线
SpringCloud Bus是用来将分布式系统的节点与轻量级消息系统链接起来的框架，它整合了Java的事件处理机制和消息中间件的功能，
目前支持的两种消息代理：RabbitMQ和Kafka  
#### 消息总线
在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个公用的消息主题，并让系统中所有微信服务实例都链接上来，由于该主题中
产生的消息会被所有实例监听和消费，称它为消息总线，在总线上的各个实例都可以方便地广播一下需要让其他链接在改主题上的示例都知道
的消息。  
基本原理:ConfigClient实例都监MQ中同一个topic（默认是SpringCloudBus）,当一个服务刷新的时候，它会把这个信息放到Topic中，这样
其它监听同一个Topic的服务就能得到通知，然后更新自身的配置。  
定点通知： curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
只通知3355的进行改变，其他的保持依旧，config-client为服务名：3355为端口号

### 六、消息驱动 spring cloud stream
官网地址：https://spring.io/projects/spring-cloud-stream  
Spring Cloud Stream is a framework for building highly scalable event-driven microservices connected with shared messaging systems.

消息驱动：屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型  
微服务应用放置同一个group中，就能保证消息只会被其中一个应用消费一次，不同的组是可以消费的，同一个组内会发生竞争关系，只有其中一个可以消费。
 
### 七、SpringCloud Sleuth 
提供了一套完整的服务器跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin  
下载zipkin：https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec
使用java -jar zipkin.jar启动zipkin server端，然后使用http://localhost:9411/zipkin 来访问  
本项目中在cloud-provider-payment8001 和cloud-consumer-order80中演示

### Nacos 动态服务发现、配置管理和服务管理平台
官网地址：https://github.com/alibaba/nacos

Nacos：Dynamic Naming and Configuration Service    
注册中心+配置中心的组合 Eureka+Config+Bus

### SpringCloud Alibaba 
官网地址 : https://spring.io/projects/spring-cloud-alibaba

Sentinel持久化配置  
在nacos创建对应的配置列表，使用json配置，配置的json如下：
 
```
[
    {
         
        "resource":"/byUrl",#资源名称
        "limitApp":"default",#来源应用
        "grade":1,#阈值类型 0表示线程数 1 表示QPS
        "count":1, #单机阈值
        "strategy":0,# 流控模式 0 表示直接，1表示关联 2 表示链路
        "controlBehavior":0, #流控效果 0表示快速失败 1 表示Warm up 2 表示排队等待
        "clusterMode":false # 是否集群
    }
]
```