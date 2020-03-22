package com.vic.springcloud.service.impl;

import com.vic.springcloud.mapper.PaymentMapper;
import com.vic.springcloud.entities.Payment;
import com.vic.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/3/15 21:29
 * @desc:
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
