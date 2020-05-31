package com.vic.seata.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author：vic
 * @date： 2020/5/24 21:31
 * @desc:
 */
@EnableDiscoveryClient
@EnableFeignClients
//取消数据源的自动创建，使用Seata的进行代理，具体在config包下的DataSourceProxyConfig类中
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataStorageMainApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApp2002.class,args);
    }
}
