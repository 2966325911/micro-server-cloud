package com.vic.springcloud.controller;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import com.vic.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Collection;
import java.util.List;


/**
 * @author：vic
 * @date： 2020/3/16 21:38
 * @desc:
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {
    public static final String PAYMENT_URL = "http://localhost:8001";
    /**
     * 如果是集群，使用服务名称调用,使用服务名称调用，利用restTemplate请求必须
     * 给RestTemplate配置相应的负载均衡机制，使其具有负载均衡能力，添加 @LoadBalanced注解即可
     */
//    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/getForEntity/{id}")
    public CommonResult getPayment2(@PathVariable("id") Long id){
        String url = PAYMENT_URL + "/payment/get/"+id;
        ResponseEntity<CommonResult> entity =  restTemplate.getForEntity(url,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }

    @GetMapping(value = "/lb")
    public String getPaymentLb(){
        List<ServiceInstance>  serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(CollectionUtils.isEmpty(serviceInstances)) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instances(serviceInstances);
        URI uri = instance.getUri();
        String url = uri + "/payment/lb";
        return restTemplate.getForObject(url,String.class);
    }

    @GetMapping("/zipkin")
    public String paymentZipkin() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
    }
}
