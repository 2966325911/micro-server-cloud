package com.vic.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author：vic
 * @date： 2020/4/27 21:35
 * @desc: sentinel 限流，在官网下载sentinel的jar包到本地，然后java -jar xx.jar 启动
 * 登录 用户名和密码均为 sentinel
 * 官网地址：https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
 * 注意：首次登录进去后，要进行接口调用后，sentinel才可以检测到
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9002.class,args);
    }
}
