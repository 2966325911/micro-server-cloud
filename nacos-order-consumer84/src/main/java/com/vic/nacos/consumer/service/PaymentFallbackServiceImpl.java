package com.vic.nacos.consumer.service;

import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @author：vic
 * @date： 2020/5/23 17:32
 * @desc:
 */
@Service
public class PaymentFallbackServiceImpl implements PaymentFallbackService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回--PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
