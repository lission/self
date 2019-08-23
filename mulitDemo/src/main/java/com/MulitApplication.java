package com;

import com.entity.Advert;
import com.mulit.service.AdvertService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author lisong@mimidai.com
 * @date 2019/8/14 17:36
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.mulit.dao")
public class MulitApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MulitApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MulitApplication.class, args);
        logger.info("Springboot MulitApplication started!");

    }

    @Autowired
    private AdvertService advertService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Advert advert = advertService.selectByPrimaryKey(1);
        logger.info("---------------------{}",advert);
    }
}
