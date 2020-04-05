package com.vic.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author：vic
 * @date： 2020/4/5 10:39
 * @desc: feign的日志调用
 */
@Configuration
public class FeignConfig {
    /**
     *  feign.Logger; feign全日制打印，开启后，可以查看feign接口调用全链路日志
     *  示例如下，调用http://localhost:80/consumer/payment/get/6 接口，日志如下
     *  2020-04-05 10:44:40.535 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     *  [PaymentFeignService#getPaymentById] <--- HTTP/1.1 200 (114ms)
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] connection: keep-alive
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] content-type: application/json
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] date: Sun, 05 Apr 2020 02:44:40 GMT
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] keep-alive: timeout=60
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] transfer-encoding: chunked
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById]
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] {"code":200,"message":"请求处理成功","data":{"id":6,"serial":"0000002"}}
     * 2020-04-05 10:44:40.536 DEBUG 17292 --- [p-nio-80-exec-2] c.v.s.service.PaymentFeignService        :
     * [PaymentFeignService#getPaymentById] <--- END HTTP (78-byte body)
     * 2020-04-05 10:44:41.469  INFO 17292 --- [erListUpdater-1]
     * @return Logger.Level
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
