package com.mulit.service;

import com.mulit.dao.AdvertMulitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Advert;

    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:39
     */
@Service
public class AdvertService{

    @Autowired
    private AdvertMulitDao advertMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return advertMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Advert record) {
        return advertMapper.insert(record);
    }

    
    public int insertSelective(Advert record) {
        return advertMapper.insertSelective(record);
    }

    
    public Advert selectByPrimaryKey(Integer id) {
        return advertMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Advert record) {
        return advertMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Advert record) {
        return advertMapper.updateByPrimaryKey(record);
    }

}
