package com.vic.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vic.springcloud.service.HystrixPaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：vic
 * @date： 2020/4/5 17:57
 * @desc:
 */
@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "global_handler")
public class PaymentController {
    @Resource
    private HystrixPaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Long id){
       return paymentService.paymentInfoOk(id);
    }


    /**
     *超时返回，当高并发的时候会出现错误，要对其进行处理
     * 进行熔断降级处理
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//                    value = "1000")
//    })
//    @HystrixCommand
    public String paymentInfoTimeout(@PathVariable("id") Long id){
        return paymentService.paymentInfoTimeout(id);
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
//
//    /**
//     * controller处理
//     * 全局异常fallback处理
//     * @retur
//     */
//    public String global_handler() {
//        return "线程池：" + Thread.currentThread().getName() + "线程池异常处理,系统异常，请稍后重试";
//    }
}
