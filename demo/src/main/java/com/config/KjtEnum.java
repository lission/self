package com.config;

/**
 * 快捷通代扣枚举类
 * @author: wangmaolin
 * @date: 2018/10/19 17:40
 * @param:
 * @return:
 */
public enum KjtEnum {

    SUCCESS("S10000", "接口调用成功"),
    DOING("P00001", "业务处理中"),

    S("成功"),
    P("处理中"),
    F("失败"),

    WAIT_BUYER_PAY("等待买家付款"),
    PAY_FINISHED("买家已付款"),
    TRADE_SUCCESS("交易支付成功"),
    TRADE_FINISHED("交易结束"),
    TRANSFER_FAIL("交易失败"),
    TRADE_CLOSED("交易关闭"),

    //等待买家付款
    W10000("WAIT_BUYER_PAY"),
    //买家已付款
    P10000("PAY_FINISHED"),
    //交易支付成功
    S10000("TRADE_SUCCESS"),
    //交易结束
    F10000("TRADE_FINISHED"),
    //交易失败
    F40000("TRANSFER_FAIL"),
    //交易关闭
    C10000("TRADE_CLOSED"),

    ICBC("ICBC","工商银行",0),
    ABC("ABC","农业银行",1),
    BOC("BOC","中国银行",0),
    CCB("CCB","建设银行",0),
    CMB("CMB","招商银行",0),
    COMM("BCOM","交通银行",0),
    CITIC("CITIC","中信银行",0),
    PSBC("PSBC","中国邮储银行",1),
    CIB("CIB","兴业银行",0),
    CMBC("CMBC","民生银行",1),
    SZPAB("PAB","平安银行",0),
    SPDB("SPDB","浦发银行",0),
    CEB("CEB","光大银行",0),
    HXB("HXB","华夏银行",1),
    GDB("GDB","广发银行",0),
    BCCB("BOB","北京银行",1),
    BJRCB("BJRCB","北京农商行",1),
    BOS("SHB","上海银行",1),
    SHRCB("SRCB","上海农商银行",1),
    CBHB("CBHB","渤海银行",1),
    HCCB("HZB","杭州银行",1),
    URCB("URCB","杭州联合银行",1),
    NBCB("NBCB","宁波银行",1),
    NJCB("NJCB","南京银行",1),
    WZCB("WZCB","温州市商业银行",1),
    CSCB("CSCB","长沙银行",1),
    CYB("CYB","集友银行",1),
    CZB("CZB","浙商银行",1),
    CZCB("CZCB","浙江稠州商业银行",1),
    GNXS("GNXS","广州市农信社",1),
    GZCB("GZCB","广州市商业银行",1),
    HKBCHINA("HKBCHINA","汉口银行",1);


    private final String code;
    private final String msg;
    private final Integer state;

    KjtEnum(String msg) {
        this.state = 1;
        this.code = this.name();
        this.msg = msg;
    }

    KjtEnum(String code, String msg) {
        this.state = 1;
        this.code = code;
        this.msg = msg;
    }

    KjtEnum(String code, String msg, Integer state) {
        this.state = state;
        this.code = code;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static boolean isSignType(String code) {
        for (KjtEnum s : KjtEnum.values()) {
            if (s.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static String getMsgBycode(String code){
        if(!isSignType(code)){
            return "";
        }
        String msg = "";
        for (KjtEnum kjtEnum : KjtEnum.values()) {
            if(kjtEnum.getCode().equals(code)){
                msg = kjtEnum.getMsg();
            }
        }
        return msg;
    }

    public static KjtEnum getBycode(String code){
        if(!isSignType(code)){
            return null;
        }
        for (KjtEnum kjtEnum : KjtEnum.values()) {
            if(kjtEnum.getCode().equals(code)){
                return kjtEnum;
            }
        }
        return null;
    }

    public static String getCodeByMsg(String msg){
        String code = "";
        for (KjtEnum kjtEnum : KjtEnum.values()) {
            if(kjtEnum.getMsg().equals(msg)){
                code = kjtEnum.getCode();
            }
        }
        return code;
    }

    public static KjtEnum getByName(String name){
        for (KjtEnum kjtEnum : KjtEnum.values()) {
            if(kjtEnum.name().equals(name)){
                return kjtEnum;
            }
        }
        return null;
    }
}
