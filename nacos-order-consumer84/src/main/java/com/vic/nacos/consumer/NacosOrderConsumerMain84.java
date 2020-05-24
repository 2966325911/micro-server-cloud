package com.vic.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author：vic
 * @date： 2020/5/23 16:34
 * @desc: nacos sentinel order consumer
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class NacosOrderConsumerMain84 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderConsumerMain84.class,args);
    }
}
