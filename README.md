# micro-server-cloud
微服务学习SpringCloud

## 负载均衡
**1、业务服务集群后问题**

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

git rm -r --cached .
git add .
git commit -m 'update .gitignore'