package com.testTemp;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utils.DateUtils;
import utils.HttpClientUtil;
import utils.Httprequests;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lisong@mimidai.com
 * @date 2019/9/2 18:16
 */
public class WechatTets {

    @Test
    public void testTemp(){
        push("","","");
    }

    private static Map<String,String> push(String formId, String openId, String wxPushId){
        Map<String,String> result = new HashMap<>();
        String accessToken = getAccessToken();
        result.put("code","-1");
        if (accessToken == null){
            return result;
        }
        Map<String,Object> postMap = new HashMap<>();
        Map<String,String> mpTemplateMap = new HashMap<>();
        Map<String,String> weappTemplateMap = new HashMap<>();
        String data = null;
        postMap.put("access_token",accessToken);
        postMap.put("touser",openId);
        mpTemplateMap.put("appid","wx2c43750e3b4aaea1");
        mpTemplateMap.put("template_id",wxPushId);
        mpTemplateMap.put("url","");
        mpTemplateMap.put("miniprogram","");
        mpTemplateMap.put("data","");
        postMap.put("mp_template_msg",mpTemplateMap);
        weappTemplateMap.put("template_id",wxPushId);
        weappTemplateMap.put("page","");
        weappTemplateMap.put("form_id",formId);
        weappTemplateMap.put("data","");
        weappTemplateMap.put("emphasis_keyword","");
        data = JSONObject.toJSONString(postMap);

        try {

            String retUrl = HttpClientUtil.post("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token"+accessToken,data);
            System.out.println("11111111111+{}"+retUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
    /**
     * 获取微信 AccessToken
     * */
    public static String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"+ "&appid="
                + "wx2c43750e3b4aaea1" + "&secret=" + "fe7e32c546cd775409a55f914ee31c07";
        try {
            String retUrl = HttpClientUtil.get(url);
            System.out.println("用户access_token Url："+retUrl);
            JSONObject json = JSONObject.parseObject(retUrl);
            return json.get("access_token").toString();
        } catch (Exception e) {
        }

        return null;
    }
    @Test
    public void test(){
        try {
            receiveUserMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveUserMsg() throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String appId = "wxa100e9c401eca3d9";
        String appSecret = "e30556bcc45b11653575e73b3e821326";
        String params = "grant_type=client_credential" + "&appid=" + appId + "&secret=" + appSecret;
        String result = Httprequests.sendGet(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);

        String access_token = jsonObject.get("access_token").toString();
        String url1 = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=" + access_token;
        String openId = "od1Ts4kbu7eOyZCTkCD0YVq9nrG0";

        JSONObject keyword1 = new JSONObject();
        JSONObject keyword2 = new JSONObject();

        JSONObject data = new JSONObject();
        JSONObject weapp_template_msg = new JSONObject();
        JSONObject param = new JSONObject();

        keyword1.put("value", "栗子");
        keyword2.put("value", DateUtils.getNow());

        data.put("keyword1", keyword1);
        data.put("keyword2", keyword2);

        weapp_template_msg.put("template_id", "fMegXrr_Jz5OhG2Bc4ShOR1pVNgRjgVhjSZ50MNH-PY");
        weapp_template_msg.put("page", "pages/index/index");
        weapp_template_msg.put("form_id", "98c626c1fa1445c4b774704493cb227e");
        weapp_template_msg.put("data", data);
        weapp_template_msg.put("emphasis_keyword", "keyword1.DATA");

        param.put("weapp_template_msg", weapp_template_msg);
        param.put("touser", openId);

        String result2 = Httprequests.call(url1, param.toJSONString());
        JSONObject resullt = JSONObject.parseObject(result2);
        System.out.println(resullt.toJSONString());
        //return success("返回成功");
    }



}
