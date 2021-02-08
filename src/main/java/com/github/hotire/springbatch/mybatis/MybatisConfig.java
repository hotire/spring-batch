package com.github.hotire.springbatch.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackageClasses = MybatisConfig.class)
@Configuration
public class MybatisConfig {
}
