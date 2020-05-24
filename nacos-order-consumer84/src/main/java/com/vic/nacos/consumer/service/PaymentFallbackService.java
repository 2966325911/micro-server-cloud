package com.vic.nacos.consumer.service;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author：vic
 * @date： 2020/5/23 17:30
 * @desc:
 * 调用才用feign
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackServiceImpl.class)
public interface PaymentFallbackService {
    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
