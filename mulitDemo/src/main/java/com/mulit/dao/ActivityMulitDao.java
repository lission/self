package com.mulit.dao;
import com.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;


/**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:29
     */
@Mapper
public interface ActivityMulitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    Activity getOneByCreateUserId(@Param("createUserId")Integer createUserId);


}