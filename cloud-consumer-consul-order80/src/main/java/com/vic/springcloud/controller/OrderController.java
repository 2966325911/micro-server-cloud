package com.vic.springcloud.controller;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author：vic
 * @date： 2020/3/16 21:38
 * @desc:
 */
@RestController
@RequestMapping("/consumer/payment")
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    /**
     * 如果是集群，使用服务名称调用,使用服务名称调用，利用restTemplate请求必须
     * 给RestTemplate配置相应的负载均衡机制，使其具有负载均衡能力，添加 @LoadBalanced注解即可
     */
    private static final String PAYMENT_URL = "http://consul-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        String url = PAYMENT_URL + "/payment/create";
        return restTemplate.postForObject(url,payment,CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        String url = PAYMENT_URL + "/payment/get/"+id;
        return restTemplate.getForObject(url,CommonResult.class);
    }

    @GetMapping("/consul")
    public String paymentInfo() {
        String url = PAYMENT_URL + "/payment/consul";
        String result = restTemplate.getForObject(url,String.class);
        return result;
    }
}
