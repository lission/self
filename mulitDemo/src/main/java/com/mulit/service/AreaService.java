package com.mulit.service;

import com.mulit.dao.AreaMulitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Area;

/**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:47
     */
@Service
public class AreaService{

    @Autowired
    private AreaMulitDao areaDao;

    
    public int deleteByPrimaryKey(Integer id) {
        return areaDao.deleteByPrimaryKey(id);
    }

    
    public int insert(Area record) {
        return areaDao.insert(record);
    }

    
    public int insertSelective(Area record) {
        return areaDao.insertSelective(record);
    }

    
    public Area selectByPrimaryKey(Integer id) {
        return areaDao.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Area record) {
        return areaDao.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Area record) {
        return areaDao.updateByPrimaryKey(record);
    }

}
