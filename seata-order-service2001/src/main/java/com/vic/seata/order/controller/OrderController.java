package com.vic.seata.order.controller;

import com.vic.seata.order.service.OrderService;
import com.vic.seata.order.domain.CommonResult;
import com.vic.seata.order.domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/5/31 10:33
 * @desc:
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 创建订单，给客户用，浏览器使用get操作
     * @param order
     * @return
     */
    @PostMapping("/order/create")
    public CommonResult create(@RequestBody Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
