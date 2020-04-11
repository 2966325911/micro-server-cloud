package com.vic.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
