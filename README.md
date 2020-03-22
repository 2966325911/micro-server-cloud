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