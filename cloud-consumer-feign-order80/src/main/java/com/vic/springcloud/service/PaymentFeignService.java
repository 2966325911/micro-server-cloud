package com.vic.springcloud.service;

import com.vic.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author：vic
 * @date： 2020/4/5 10:03
 * @desc:
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    /**
     * 调用超时测试,openFeign 默认1s超时，会返回报错
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}
