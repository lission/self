package com.entity;

import java.io.Serializable;
import lombok.Data;


    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:47
     */
@Data
public class Area implements Serializable {
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 上级
    */
    private Integer parentId;

    /**
    * 级别
    */
    private Integer level;

    private static final long serialVersionUID = 1L;
}