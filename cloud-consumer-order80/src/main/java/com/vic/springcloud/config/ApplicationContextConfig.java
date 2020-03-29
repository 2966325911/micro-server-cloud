package com.vic.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author：vic
 * @date： 2020/3/16 21:40
 * @desc:
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * 将需要加载的类注入spring 容器
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
