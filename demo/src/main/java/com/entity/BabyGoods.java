package com.entity;

import lombok.Data;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/27 17:01
 */
@Data
public class BabyGoods extends Goods{
/*    private String name;
    private Float price;
    private Long count;*/
    private Integer age;
    
    
    public synchronized void buy(){
    
        
    }
}
