package com.vic.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：vic
 * @date： 2020/4/12 11:36
 * @desc: 利用代码实现路由
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //对guonei进行路由，访问的http://localhost:9527/guonei 路由到 http://news.baidu.com/guonei
        return builder
                .routes()
                .route("path_route_vic", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
    }
}
