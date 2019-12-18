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
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author lisong@mimidai.com
 * @date 2019/8/14 17:36
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mulit.dao")
public class SelfApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(SelfApplication.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SelfApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SelfApplication.class, args);
        logger.info("Springboot Application [demo] started!");
    }
}
