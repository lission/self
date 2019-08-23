package com.testTemp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisong@mimidai.com
 * @date 2019/4/29 13:57
 */
public class TerminalInfo {
    private Map<String,String> merchant_custom;
    private String return_url;
    private static Map<String, String> merchant_custom_init = new HashMap<String, String>(){
        {
            put("terminal_type","99");
        }
    };

    public TerminalInfo() {
        this.merchant_custom = merchant_custom_init;
    }

    public Map<String, String> getMerchant_custom() {
        return merchant_custom;
    }

    public void setMerchant_custom(Map<String, String> merchant_custom) {
        this.merchant_custom = merchant_custom;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    @Override
    public String toString() {
        return "TerminalInfo{" +
                "merchant_custom=" + merchant_custom +
                ", return_url='" + return_url + '\'' +
                '}';
    }
}
