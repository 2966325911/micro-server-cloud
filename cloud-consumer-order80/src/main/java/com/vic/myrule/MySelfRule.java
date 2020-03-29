package com.vic.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：vic
 * @date： 2020/3/29 17:29
 * @desc:  自定义Ribbon规则，指定使用系统自带的规则
 * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际请求服务器的下标
 * 每次服务器启动后rest接口计数从1开始
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
//        return new BestAvailableRule();
//        return new RoundRobinRule();
//        return new  WeightedResponseTimeRule();
//        return new RetryRule();
//        return new AvailabilityFilteringRule();
//        return new ZoneAvoidanceRule();
        //定义为随机访问规则
        return new RandomRule();
    }
}
