package com.vic.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author：vic
 * @date： 2020/4/12 12:31
 * @desc: 全局过滤器，设置后请求必须带uname参数
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("-----------come in MyLogGatewayFilter:" + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(StringUtils.isEmpty(uname)){
            log.info("----------用户名为null，非法用户-----");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    /**
     * 过滤器的顺序，全局的顺序为0
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
