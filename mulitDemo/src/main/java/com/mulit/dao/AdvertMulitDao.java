package com.mulit.dao;

import com.entity.Advert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:39
     */
@Mapper
public interface AdvertMulitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Advert record);

    int insertSelective(Advert record);

    Advert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advert record);

    int updateByPrimaryKey(Advert record);


}