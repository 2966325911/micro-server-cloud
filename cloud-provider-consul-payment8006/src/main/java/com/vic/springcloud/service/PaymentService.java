package com.vic.springcloud.service;

import com.vic.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author：vic
 * @date： 2020/3/15 21:29
 * @desc:
 */
public interface PaymentService {
    /**
     * 创建数据
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 查找数据
     * @param id
     * @return
     */
    Payment getPaymentById( Long id);
}
