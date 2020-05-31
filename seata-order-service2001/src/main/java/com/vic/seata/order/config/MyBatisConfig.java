package com.vic.seata.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author：vic
 * @date： 2020/5/31 10:37
 * @desc:
 */
@Configuration
@MapperScan("{com.vic.seata.order.dao}")
public class MyBatisConfig {

}
