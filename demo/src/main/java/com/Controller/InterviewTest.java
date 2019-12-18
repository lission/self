package com.Controller;

import com.Service.DisCountService;
import com.entity.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

/**
 * @author lisong@mimidai.com
 * @date 2019/11/27 10:13
 */
@Component
public class InterviewTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DisCountService disCountService;
    
    private static final Map<Integer,String> MESSAGE_MAP=new HashMap<Integer,String>(){
    };
    {
        MESSAGE_MAP.put(1,"恭喜您帮好友砍价10元");
        MESSAGE_MAP.put(2,"恭喜您帮好友砍价9元");
        MESSAGE_MAP.put(3,"恭喜您帮好友砍价8元");
        MESSAGE_MAP.put(4,"恭喜您帮好友砍价7元");
        MESSAGE_MAP.put(5,"恭喜您帮好友砍价6元");
        MESSAGE_MAP.put(6,"恭喜您帮好友砍价5元");
        MESSAGE_MAP.put(7,"恭喜您帮好友砍价4元");
        MESSAGE_MAP.put(8,"恭喜您帮好友砍价3元");
        MESSAGE_MAP.put(9,"恭喜您帮好友砍价2元");
        MESSAGE_MAP.put(10,"恭喜您帮好友砍价1元");
    }
    
    
    /**
     * 要求实现一个类似拼多多分享砍价的功能，每一个用户可以通过分享给好友，让好友帮忙砍价，
     * 最多可以有10个不同的好友帮他砍价，第一个提示“恭喜您帮好友砍价10元”……直到最后一
     * 个“恭喜您帮好友砍价1元”，考虑高并发的场景。要求写一下存储结构和业务逻辑的伪代码
     * */
    /**
     * 主要思路:通过redis实现锁，使用到了redis存储set的不可重复性，已经redis自增长功能
     * 返回信息存储在map中
     * */
    
    public String disCount(String linkId,String userId,String friendId){
        String resp ="";
        if ("end".equals(redisTemplate.opsForValue().get("linkId"+"end"))){
            return "砍价已结束";
        }
        Long l = redisTemplate.opsForSet().add(linkId+"set",friendId);
        System.out.println(l);
        if (l==0){
            return "您已帮好友砍价成功";
        }
        Integer integer =
            redisTemplate.opsForValue().increment(linkId).intValue();
        if (integer >10){
            redisTemplate.opsForValue().setIfAbsent("linkId"+"end","end");
            return "您来晚喽，砍价已结束";
        }
        /**
         * 砍价业务
         * MQ实现
         *
         * */
        disCountService.countPrice(userId,friendId);
        resp = MESSAGE_MAP.get(integer);
        System.out.println(redisTemplate.opsForSet().members(linkId+"set"));
        return resp;
    }
    
    
    
    /**
     * 商品抢购
     * 对某一商品goods ，库存count固定，不能超卖
     *
     * */


    public Boolean buyDoods(Goods goods, String userId){
        
        
        return true;
    }
}
