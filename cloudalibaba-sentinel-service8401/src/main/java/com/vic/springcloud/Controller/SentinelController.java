package com.vic.springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author：vic
 * @date： 2020/5/16 17:50
 * @desc: sentinel 流控
 * 流控效果： warm up 相当于预热，设置时间预热时间，例如5s前进行预热如果调用量过大将会有失败，
 * 预热完成后，将不会有失败数据
 */
@RestController
//@RequestMapping("/sentinel")
@Slf4j
public class SentinelController {
    @GetMapping("/testA")
    public String testA(){
        //测试 用sentinel进行线程流控
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testA==========");
        return "testA------------";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info("testB==========");
        return "testB------------";
    }

    /**
     * 测试异常数 降级
     * @return
     */
    @GetMapping("/testC")
    public String testC(){
        log.info("testC==========");
        int i = 10/0;
        return "testC------------";
    }


    /**
     * 测试异常数 降级
     * 热点规则的限流，对热点参数进行限流，如果不设置blockHandler则，限流后会报white Error Page,
     * 不友好，设置对应的处理方法，较为友好
     * 对热点参数进行限流：可以对 传入的第一个参数或者其他参数进行配置
     * 也可以对特殊的值进行过滤，当等于某个值是，不进行限流或者限流放宽，sentinel都可以做到
     * @SentinelResource 管理运行时出错，blockHandler为兜底方法，如果发生异常Exception，也会走起指定的方法
     *
     * @return
     */
    @GetMapping("/testHotKey")
    //资源名 用于添加热点规则名用 ，blockHandler 的方法是兜底方法，
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){

        return "--------------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------------------deal_testHotKey";
    }
}
