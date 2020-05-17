package com.vic.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vic.springcloud.entities.CommonResult;

/**
 * @author：vic
 * @date： 2020/5/17 21:36
 * @desc: @SentinelResource 中的 异常统一处理类
 * 异常处理累的方法必须是public static的
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"按客户自定义库的，global handlerException...");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"按客户自定义库的，global handlerException222...");
    }
}
