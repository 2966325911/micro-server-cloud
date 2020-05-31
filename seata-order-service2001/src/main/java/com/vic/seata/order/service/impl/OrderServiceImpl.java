package com.vic.seata.order.service.impl;

import com.vic.seata.order.service.AccountService;
import com.vic.seata.order.service.OrderService;
import com.vic.seata.order.dao.OrderDao;
import com.vic.seata.order.domain.Order;
import com.vic.seata.order.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/5/31 10:14
 * @desc:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderMapper;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单
     * @param order
     */
    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("----->开始创建订单");
        order.setStatus(0);
        //1 新建订单
        orderMapper.create(order);

        log.info("------>订单微服务开始调用库存，做扣减count");
        // 2 扣减库存
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>订单微服务开始调用库存，做扣减end");

        log.info("------>订单微服务开始调用账户，做扣减Money");
        // 3 扣减用户金额
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------>订单微服务开始调用账户，做扣减End");

        log.info("------->修改订单状态start");
        // 4 修改订单的状态 从0 -> 1
        orderMapper.updateStatus(order.getUserId(),0);
        log.info("------->修改订单状态end");

        log.info("-------->下订单结束了，O(∩_∩)O哈哈~");
    }

    /**
     * 更新订单
     * @param userId 用户id
     * @param status 状态
     */
    @Override
    public void update(Long userId, Integer status) {

    }
}
