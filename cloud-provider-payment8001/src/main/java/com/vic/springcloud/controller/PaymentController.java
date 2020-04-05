package com.vic.springcloud.controller;

import com.vic.springcloud.common.ResponseResult;
import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import com.vic.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：vic
 * @date： 2020/3/15 21:33
 * @desc:
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String serviceId;
    /**
     * 用于服务发现获取该服务的基础信息,用于服务发现
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("--------插入结果--" + result + "serverPort" + serverPort);
        if (result > 0) {
            return new ResponseResult().success();
        }
        return new ResponseResult().fail();
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("--------获取成功--serverPort=" + serverPort);
        if (payment != null) {
            return new ResponseResult().success(payment);
        }
        return new ResponseResult().fail("没有对应的记录");
    }

    @GetMapping(value = "/discovery")
    public Object getService() {
        List<String> services = discoveryClient.getServices();
        services.forEach(element -> {
            log.info("--element--" + element);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        instances.forEach(instance -> {
            log.info(instance.getServiceId() + "\t" +
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri());
        });
        return this.discoveryClient;
    }

    @GetMapping("/lb")
    public String getLb(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}


