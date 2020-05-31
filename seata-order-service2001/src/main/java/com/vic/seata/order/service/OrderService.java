package com.vic.seata.order.service;

import com.vic.seata.order.domain.Order;

/**
 * @author：vic
 * @date： 2020/5/31 10:12
 * @desc:
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param userId 用户id
     * @param status 状态
     */
    void update(Long userId,Integer status);
}
