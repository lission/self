package com.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 * @author EVE
 */
@Configuration
@MapperScan(basePackages = "com.mulit.dao")
public class MyBatisConfig {


    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource(){
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
