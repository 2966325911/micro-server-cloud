package com.vic.seata.order.dao;

import com.vic.seata.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author：vic
 * @date： 2020/5/24 21:32
 * @desc:
 */
@Mapper
public interface OrderDao {
    /**
     * 新建订单 此处order中的status 为0
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param userId 用户id
     * @param status 状态
     */
    void updateStatus(@Param("userId") Long userId,@Param("status") Integer status);
}
