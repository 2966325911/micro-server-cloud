package com.vic.springcloud.controller;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


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
}
