package com.mulit.dao;

import com.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;


/**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:47
     */
@Mapper
public interface AreaMulitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}