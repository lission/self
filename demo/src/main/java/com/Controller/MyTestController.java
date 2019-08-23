package com.Controller;

import com.mulit.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lisong@mimidai.com
 * @date 2019/8/15 11:59
 */
public class MyTestController {
    private static final Logger logger = LoggerFactory.getLogger(MyTestController.class);

    @Autowired
    private AdvertService advertService;
    
    public void selectTest(){
        
    }
}
