package com.vic.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：vic
 * @date： 2020/5/23 16:15
 * @desc:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosPaymentProviderMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentProviderMain9003.class,args);
    }
}
