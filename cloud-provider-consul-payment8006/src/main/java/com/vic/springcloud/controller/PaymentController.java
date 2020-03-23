package com.vic.springcloud.controller;

import com.vic.springcloud.common.ResponseResult;
import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import com.vic.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("--------插入结果--" + result + "serverPort:" + serverPort);
        if (result > 0) {
            return new ResponseResult().success();
        }
        return new ResponseResult().fail();
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new ResponseResult().success(payment);
        }
        return new ResponseResult().fail("没有对应的记录");
    }

    @GetMapping("/consul")
    public String paymentZk() {
        return "springCloud with consul:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}


