package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;


    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:29
     */
@Data
public class Activity implements Serializable {
    private Integer id;

    /**
    * 活动名称
    */
    private String name;

    /**
    * 活动编号
    */
    private String code;

    /**
    * 开始时间
    */
    private Date startTime;

    /**
    * 结束时间
    */
    private Date endTime;

    /**
    * 状态，1未开始2进行中3已结束
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人id
    */
    private Integer createUserId;

    /**
    * 活动金额
    */
    private BigDecimal money;

    /**
    * 更新人id
    */
    private Integer updateUserId;

    private static final long serialVersionUID = 1L;
}