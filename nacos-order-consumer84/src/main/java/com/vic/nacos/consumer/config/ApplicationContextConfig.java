package com.vic.nacos.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author：vic
 * @date： 2020/5/23 16:39
 * @desc:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //开启负载均衡
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
