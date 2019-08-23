package com.mulit.dao;

import com.entity.Area;
import org.apache.ibatis.annotations.Mapper;


    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:47
     */
@Mapper
public interface AreaDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}