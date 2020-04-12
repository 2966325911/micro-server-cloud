package com.vic.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author：vic
 * @date： 2020/4/12 17:37
 * @desc: SpringCloud Config配置中心 Config有server和client端，此处为server端
 * 启动成功后使用http://localhost:3344/master/config-dev.yml 地址进行访问，可以获取git相关文件的配置
 * /master/config-dev.yml为git 中springcloud Config的配置
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
