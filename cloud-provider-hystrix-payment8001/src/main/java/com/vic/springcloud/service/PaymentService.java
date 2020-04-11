package com.vic.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author：vic
 * @date： 2020/4/5 17:20
 * @desc: 这里直接在service中写，只为测试功能
 */
@Service
public class PaymentService {

    public String paymentInfoOk(Long id) {
        String info = "线程池：" + Thread.currentThread().getName() + " paymentOk ,id+" +
                id + "\t";
        return info;
    }

    //---------------------- 服务熔断-----------------------
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "3000")
    })
    public String paymentInfoTimeOut(Long id) {
        String info = "线程池：" + Thread.currentThread().getName() + " paymentTimeOut ,id+" +
                id + "\t";
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 服务降级方法，当出错后则进行此方法调用
     * 服务降级既可以放在客户端，也可以放在服务端，一般放在客户端降级
     * @param id
     * @return
     */
    public String paymentInfo_TimeoutHandler(Long id) {
        return "线程池：" + Thread.currentThread().getName() + "线程池异常处理,系统异常，请稍后重试";
    }

    //----------------------服务降级---------------------


    /**
     * 次数@HystrixProperty的参数配置，在源码HystrixCommandProperties.class均可找到，具体含义可
     * 去官网找对应api说明进行理解并尝试
     * 在10秒窗口期中10次请求有6次是请求失败的,断路器将起作用
     *
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期/时间范文
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
    }
    )
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数,请稍后重试,o(╥﹏╥)o id:" + id;
    }

}
