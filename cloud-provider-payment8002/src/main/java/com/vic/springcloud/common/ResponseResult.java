package com.vic.springcloud.common;

import com.vic.springcloud.entities.CommonResult;

/**
 * @author：vic
 * @date： 2020/3/15 21:37
 * @desc:
 */
public class ResponseResult{
    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 404;

    public CommonResult success(){
        return new CommonResult(SUCCESS_CODE,"请求处理成功",null);
    }

    public CommonResult<Object> success(Object data){
        return new CommonResult<>(SUCCESS_CODE,"请求处理成功",data);
    }

    public CommonResult fail(){
        return new CommonResult(FAIL_CODE,"请求处理失败",null);
    }

    public CommonResult<Object> fail(Object data){
        return new CommonResult<>(FAIL_CODE,"请求处理失败",data);
    }
}
