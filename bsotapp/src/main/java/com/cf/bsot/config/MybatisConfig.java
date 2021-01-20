package com.cf.bsot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/15 23:14
 */
@Configuration
@MapperScan("com.cf.bsot.mapper")
public class MybatisConfig {
}
