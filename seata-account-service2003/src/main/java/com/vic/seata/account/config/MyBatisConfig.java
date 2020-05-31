package com.vic.seata.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author：vic
 * @date： 2020/5/31 10:37
 * @desc:
 */
@Configuration
@MapperScan("{com.vic.seata.account.dao}")
public class MyBatisConfig {

}
