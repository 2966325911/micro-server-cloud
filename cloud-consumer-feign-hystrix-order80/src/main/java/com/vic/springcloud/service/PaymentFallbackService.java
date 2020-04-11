package com.vic.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author：vic
 * @date： 2020/4/5 18:39
 * @desc:
 */
@Component
public class PaymentFallbackService implements HystrixPaymentService {
    @Override
    public String paymentInfoOk(Long id) {
        return "----------paymentInfoOk--------- fallback";
    }

    @Override
    public String paymentInfoTimeout(Long id) {
        return "----------paymentInfoTimeout--------- fallback";
    }
}
