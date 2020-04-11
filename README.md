# micro-server-cloud
# 微服务学习SpringCloud

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


###负载均衡
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