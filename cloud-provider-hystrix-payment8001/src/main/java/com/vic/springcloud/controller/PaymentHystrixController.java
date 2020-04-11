package com.vic.springcloud.controller;

import com.vic.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/4/5 17:25
 * @desc:
 */
@RestController
public class PaymentHystrixController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Long id){
        String result =  paymentService.paymentInfoOk(id);

        return result;
    }


    /**
     *超时返回，当高并发的时候会出现错误，要对其进行处理
     * 进行熔断降级处理
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Long id){
        String result =  paymentService.paymentInfoTimeOut(id);

        return result;
    }

}
