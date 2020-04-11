package com.vic.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author：vic
 * @date： 2020/4/5 17:55
 * @desc:
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface HystrixPaymentService {
    @GetMapping("/payment/hystrix/ok/{id}")
   String paymentInfoOk(@PathVariable("id") Long id);


    /**
     * 超时返回，当高并发的时候会出现错误，要对其进行处理
     * 进行熔断降级处理
     *
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfoTimeout(@PathVariable("id") Long id);

}
