package com.mulit.service;

import com.mulit.dao.ActivityMulitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Activity;

    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:29
     */
@Service
public class ActivityService{

    @Autowired
    private ActivityMulitDao activityMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return activityMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Activity record) {
        return activityMapper.insert(record);
    }

    
    public int insertSelective(Activity record) {
        return activityMapper.insertSelective(record);
    }

    
    public Activity selectByPrimaryKey(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Activity record) {
        return activityMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Activity record) {
        return activityMapper.updateByPrimaryKey(record);
    }

}
