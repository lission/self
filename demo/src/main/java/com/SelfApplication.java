package com;

import com.entity.Advert;
import com.mulit.service.AdvertService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lisong@mimidai.com
 * @date 2019/8/14 17:36
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mulit.dao")
public class SelfApplication implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SelfApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SelfApplication.class, args);
        logger.info("Springboot SelfApplication started!");
    }

    @Autowired
    private AdvertService advertService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("开始============");
        Advert advert = advertService.selectByPrimaryKey(2);
        logger.info("{}",advert.toString());
        logger.info("结束============");

    }
}
