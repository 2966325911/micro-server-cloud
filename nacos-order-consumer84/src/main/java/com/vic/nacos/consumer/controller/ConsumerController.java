package com.vic.nacos.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.vic.nacos.consumer.fallback.ConsumerFallback;
import com.vic.nacos.consumer.service.PaymentFallbackService;
import com.vic.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author：vic
 * @date： 2020/5/23 16:40
 * @desc: 采用feign 和ribbon 做负载
 *
 */
@RestController
public class ConsumerController {
    //    @Value("${server-url.nacos-user-service}")
//    private String serverUrl;
    private static final String SERVICE_URL = "http://nacos-payment-provider";
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private PaymentFallbackService paymentFallbackService;

    // fallback 是运行时异常 ，java异常
    // blockHandler 只管sentinel配置违规
    @GetMapping("/consumer/fallback/{id}")
    // 无任何配置
//    @SentinelResource(value = "fallback")
    //配置方法异常
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    //进行 fallback和block配置，即处理java异常又处理sentinel配置异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler",fallback = "handlerFallback")
    //异常忽略exceptionsToIgnore
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler",fallback = "handlerFallback"
//    ,exceptionsToIgnore = {IllegalArgumentException.class})
    // 配置fallbackClass的时候，必须用fallback 明确支持对应fallbackClass中调用的方法
    @SentinelResource(value = "fallback", fallbackClass = ConsumerFallback.class,fallback = "handlerFallback")
    public CommonResult paymentCommonResult(@PathVariable Long id) {
        CommonResult commonResult = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class,
                id);
        // 4 和其他 判断是为了给做fallback相关的逻辑 有意而为之
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        } else if (commonResult.getData() == null) {
            throw new NullPointerException("NullPointerException 该id没有相应的记录");
        }

        return commonResult;
    }

//    public CommonResult blockHandler(@PathVariable Long id, Throwable e) {
//        Payment payment = new Payment(id, "null");
//        return new CommonResult(445, "兜底异常blockHandler，exception内容" + e.getMessage(), payment);
//    }
//
//    public CommonResult handlerFallback(@PathVariable Long id, BlockException e) {
//        Payment payment = new Payment(id, "null");
//        return new CommonResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
//    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id){
        return paymentFallbackService.paymentSQL(id);
    }

}
