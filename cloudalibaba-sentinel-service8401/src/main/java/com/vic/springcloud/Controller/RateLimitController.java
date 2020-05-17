package com.vic.springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import com.vic.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：vic
 * @date： 2020/5/17 21:20
 * @desc:
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称进行限流测试Ok",new Payment(1L,"serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName());
    }

    /**
     * 按照url进行限流
     * @return
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "/byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按照url进行限流测试oK",new Payment(2020L,"serial002"));
    }

    /**
     * 统一进行处理限流异常处理类
     * @SentinResource 中value 对应的值为指定限流的名称
     * blockHandlerClass 指定兜底的异常类，blockHandler 指定处理类中的具体的处理方法
     * 如果blockHandlerClass只有一个方法，则无需指定blockHandler
     * @return
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"自定义统一处理异常类进行限流测试oK",new Payment(2020L,"serial003"));
    }
}
