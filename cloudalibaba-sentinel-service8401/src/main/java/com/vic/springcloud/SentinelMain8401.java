package com.vic.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：vic
 * @date： 2020/5/16 17:46
 * @desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain8401 {
    public static void main(String[] args){
        SpringApplication.run(SentinelMain8401.class,args);
    }
}
