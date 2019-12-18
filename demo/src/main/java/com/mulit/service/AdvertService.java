package com.mulit.service;

import com.mulit.dao.AdvertDao;
import com.mulit.dao.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.entity.Advert;
import org.springframework.transaction.annotation.Transactional;

/**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:39
     */
@Service
public class AdvertService{

    @Resource
    private AdvertDao advertMapper;
    @Autowired
    private AreaDao areaDao;
    
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

    @Transactional
    public void updateTest(Advert advert){
        advertMapper.insert(advert);
    }

}
