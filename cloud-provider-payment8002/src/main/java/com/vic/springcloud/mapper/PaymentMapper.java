package com.vic.springcloud.mapper;

import com.vic.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author：vic
 * @date： 2020/3/15 20:51
 * @desc:
 */
@Mapper
public interface PaymentMapper {
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
    Payment getPaymentById(@Param("id") Long id);
}
