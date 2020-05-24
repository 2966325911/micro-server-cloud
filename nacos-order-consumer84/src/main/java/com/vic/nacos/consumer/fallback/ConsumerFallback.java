package com.vic.nacos.consumer.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vic.springcloud.entities.CommonResult;
import com.vic.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author：vic
 * @date： 2020/5/23 16:59
 * @desc:
 */

public class ConsumerFallback {

    /**
     * handler Fallback兜底异常，处理方法必须是static，调用方必须明确指出是调用那个方法
     * @param id
     * @param e
     * @return
     */
    public static CommonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
    }
}
