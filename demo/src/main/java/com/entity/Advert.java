package com.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


    /**
     * @author lisong@mimidai.com
     * @date 2019/8/14 16:39
     */
@Data
public class Advert implements Serializable {
    private Integer id;

    /**
    * 广告名称
    */
    private String name;

    /**
    * 链接地址
    */
    private String url;

    /**
    * 广告公司
    */
    private String company;

    /**
    * 开始时间
    */
    private Date startTime;

    /**
    * 结束时间
    */
    private Date endTime;

    /**
    * 下线时间
    */
    private Date offTime;

    /**
    * 访客量
    */
    private Integer userSessions;

    /**
    * 图片地址
    */
    private String adPicUrl;

    /**
    * 安卓发布位置（1-闪屏;2-借款页面;3-还款页面;4-进度查询页面;5-借款+还款）
    */
    private String androidType;

    /**
    * 安卓平台（1-有效）
    */
    private String android;

    /**
    * IOS发布位置（1-闪屏;2-借款页面;3-还款页面;4-进度查询页面;5-借款+还款）
    */
    private String iosType;

    /**
    * ios平台（1-有效）
    */
    private String ios;

    /**
    * 微信发布位置（1-微信还款列表页面）
    */
    private String wechatType;

    /**
    * 微信公众号平台（1-有效）
    */
    private String wechat;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人id
    */
    private Integer createUserId;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 修改人id
    */
    private Integer updateUserId;

    /**
    * 系统分支：0-米米贷；1-米米店 ;2-大白猫
    */
    private String branch;

    /**
    * 广告类型（0-普通广告；1-轮播广告）
    */
    private String advertType;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 状态（0-下架，1-上架）
    */
    private String state;

    /**
    * ios极速平台（1-有效）
    */
    private String iosExtreme;

    /**
    * IOS极速发布位置（1-闪屏;2-借款页面;3-还款页面;4-进度查询页面;5-借款+还款）
    */
    private String iosExtremeType;

    private static final long serialVersionUID = 1L;
}