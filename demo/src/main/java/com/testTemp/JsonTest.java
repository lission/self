package com.testTemp;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.junit.Test;
import utils.Json2MapUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lisong@mimidai.com
 * @date 2018/5/30 18:06
 */
public class JsonTest {

    public static void main(String[] args) {
        OrderListZhiduoduo();
    }
    /**
     * 职多多 招工信息列表
     */
    public static void OrderListZhiduoduo(){

        String test = "{\n" +
                "    \"status\": \"y\",\n" +
                "    \"code\": \"1000000\",\n" +
                "    \"info\": \"正确\",\n" +
                "    \"param\": {\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"zhaopinid\": 221,\n" +
                "                \"update_time\": \"2018-05-02\",\n" +
                "                \"title\": \"青岛中集普工焊工（不..\",\n" +
                "                \"zhgongzi\": \"5500-6500\",\n" +
                "                \"zhfanfei\": \"700元\",\n" +
                "                \"zhfanfei_content\": \"一次性返费\",\n" +
                "                \"city\": \"山东-青岛\",\n" +
                "                \"tags\": \"<a href=\\\"javascript:void(0)\\\">有餐补</a> \",\n" +
                "                \"tagsformat\": [\n" +
                "                    \"有餐补\",\n" +
                "                    \"住宿\"\n" +
                "                ],\n" +
                "                \"sexage\": \"男18-38周岁\",\n" +
                "                \"isjiedan\": 0,\n" +
                "                \"fanfeitype1alias\": \"一次性返费\",\n" +
                "                \"fanfeitype2alias\": \"\",\n" +
                "                \"fanfeitype3alias\": \"\"\n" +
                "            }\n" +
                "           \n" +
                "        ],\n" +
                "        \"page_next\": 2,\n" +
                "        \"islast\": 0\n" +
                "    }\n" +
                "}\n";
        JSONObject reqStr1 = JSONObject.parseObject(test.trim());

        JSONObject subReqStr1 = reqStr1.getJSONObject("param");
        JSONArray jArray = subReqStr1.getJSONArray("data");
        for(int i = 0 ;i<jArray.size();i++){
            JSONObject temp =  (JSONObject)jArray.get(i);
            System.out.println("i++++++++++++"+temp);
            System.out.println("q++++++++++++"+temp.getString("sexage").substring(0,1));
            System.out.println("q1++++++++++++"+temp.getString("sexage").substring(1));
            System.out.println("a++++++++++++"+temp.getString("zhaopinid"));
            System.out.println("a++++++++++++"+temp.getLong("zhaopinid"));
            String[] strings = temp.getString("city").split("-");
            if (strings.length >= 2){
                System.out.println(strings[0]);
                System.out.println(strings[1]);
            }
            JSONArray tagsArray = temp.getJSONArray("tagsformat");
            for (int j = 0;j<tagsArray.size();j++){
                System.out.println(tagsArray.get(j));
            }
        }

    }

    @Test
    public void subStringTest(){
        Gson gson = new Gson();
//        String s = "\"\"";
        String s = "111";
        System.out.println(gson.fromJson(s,Student.class));
    }

    @Test
    public void mapTest(){
        List<String> list = CODE_MAP.get("1");
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
    /**
     * 合利宝代付银行码映射
     */
    public static Map<String,List<String>> CODE_MAP = new HashMap<String, List<String>>();
    static {
        CODE_MAP.put("1",new ArrayList<String>(){{add("CMBCHINA"); add("招商银行");}});
        CODE_MAP.put("7",new ArrayList<String>(){{add("BOCO"); add("交通银行");}});
        CODE_MAP.put("10",new ArrayList<String>(){{add("CGB"); add("广发银行");}});
        CODE_MAP.put("11",new ArrayList<String>(){{add("ECITIC"); add("中信银行");}});
        CODE_MAP.put("27",new ArrayList<String>(){{add("PINGAN"); add("平安银行");}});
        CODE_MAP.put("19",new ArrayList<String>(){{add("BCCB"); add("北京银行");}});
        CODE_MAP.put("22",new ArrayList<String>(){{add("BON"); add("南京银行");}});
        CODE_MAP.put("17",new ArrayList<String>(){{add("SRCB"); add("上海农商银行");}});
        CODE_MAP.put("28",new ArrayList<String>(){{add("HSBANK"); add("徽商银行");}});
        CODE_MAP.put("26",new ArrayList<String>(){{add("HZBANK"); add("杭州银行");}});
        CODE_MAP.put("14",new ArrayList<String>(){{add("GZRCBANK"); add("广州农村商业银行");}});
        CODE_MAP.put("15",new ArrayList<String>(){{add("BOG"); add("广州银行");}});

    }

    @Test
    public void hashMapTest(){
        HashMap<String,String> map = new HashMap<String, String>();
        ConcurrentHashMap<String,String> map1 = new ConcurrentHashMap<String, String>();
        map.put("apple","5000");
        map.put("xiaomi","5000");
        System.out.println(map.hashCode());
    }

    @Test
    public void amoutTest(){
        BigDecimal a = new BigDecimal(0);
        BigDecimal d = new BigDecimal(0);
        BigDecimal b = new BigDecimal(4500);
        BigDecimal c = new BigDecimal(55);
       /* String s = "0.3";
        a = b.multiply(new BigDecimal(s)).setScale(0,BigDecimal.ROUND_UP);
        d = c.divide(new BigDecimal(100));*/
       BigDecimal e = b.add(new BigDecimal(3000));
        System.out.println(b.divide(BigDecimal.valueOf(100)).setScale(0));

    }

    @Test
    /**
     * 格式化时间字符串返回Date
     *
     * @param timestamp 时间戳字符串
     * @param pattern   字符串模式 例如:yyyy-MM-dd
     * @return 格式化后的时间类
     */
    public void parseTimeStamp() {
        Date timestamp = new Date();
        System.out.println(timestamp);
        String pattern = "yyyy-MM";
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            String str = sdf.format(timestamp);
            System.out.println(str);
            result = sdf.parse(str);
            System.out.println(result);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Test
    public void stringbufferTest(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("该笔订单已存在对平记录，批次号【");
        stringBuffer.append("2018080890808");
        stringBuffer.append("】");
        System.out.println(stringBuffer);
    }

    @Test
    public void dateBetween(){
        String smdate = "2018-07-09 13";
        String bdate = "2018-07-10 12";

        System.out.println(smdate.replace("-","&"));
        System.out.println(bdate);
    }

    @Test
    public void randomTest(){
        /*for (int j=0;j<20;j++){
            String s = String.valueOf((Math.random()*9+1)*10000).substring(0,5);
            String s1 = DateUtils.format(new Date(),"yyyyMMddHHmmss");
            String s2 = s1+s;
            System.out.println(s);
            System.out.println(s1);
            System.out.println(s2);

        }*/
        int a = new BigDecimal("200").compareTo(new BigDecimal(1000));
        System.out.println(a);
    }
    @Test
    public void outTest(){
        System.out.println(String.valueOf(System.currentTimeMillis()).substring(0,10));
    }

    @Test
    public void sortTest(){
        SortedMap<String, String> qParam = new TreeMap<String,String>();
        qParam.put("amethod", "1");
        qParam.put("bversion", "1");
        qParam.put("cappid", "1");
        qParam.put("dmch_id", "1");
        qParam.put("eout_trade_no","1");
        qParam.put("fout_refund_no","1");
        qParam.put("hnonce_str","1");
        List<String> list = new ArrayList<>(qParam.keySet());
        Collections.sort(list);
        System.out.println("升序"+list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("降序"+list);
    }

    @Test
    public void compareTest(){
        SortedMap<String, String> qParam = new TreeMap<String,String>();
        qParam.put("amethod", "1");
        qParam.put("bversion", "1");
        qParam.put("cappid", "1");
        qParam.put("dmch_id", "1");
        qParam.put("eout_trade_no","1");
        qParam.put("fout_refund_no","1");
        qParam.put("hnonce_str","1");
        List<String> list = new ArrayList<>(qParam.keySet());
    }
    @Test
    public  void j2mTest() {
        String js = "{\n" +
                "\t\"code\": \"0\",\n" +
                "\t\"msg\": \"查询还款信息成功\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"count\": 0,\n" +
                "\t\t\"contractNum\": \"201809044155016767\",\n" +
                "\n" +
                "\t\t\"user\": {\n" +
                "\t\t\t\"id\": null,\n" +
                "\t\t\t\"userId\": 4155016,\n" +
                "\t\t\t\"name\": \"徐春颖\",\n" +
                "\t\t\t\"idcard\": \"211122199310203327\",\n" +
                "\t\t\t\"idcardProvince\": null,\n" +
                "\t\t\t\"idcardCity\": null,\n" +
                "\t\t\t\"idcardArea\": null,\n" +
                "\t\t\t\"idcardAddress\": null,\n" +
                "\t\t\t\"nativePlace\": null,\n" +
                "\t\t\t\"sex\": null,\n" +
                "\t\t\t\"birthday\": null,\n" +
                "\t\t\t\"age\": null,\n" +
                "\t\t\t\"phone\": \"18910075659\",\n" +
                "\t\t\t\"phoneRegion\": null,\n" +
                "\t\t\t\"address\": null,\n" +
                "\t\t\t\"marriage\": null,\n" +
                "\t\t\t\"marriageName\": null,\n" +
                "\t\t\t\"children\": null,\n" +
                "\t\t\t\"childrenName\": null,\n" +
                "\t\t\t\"qq\": null,\n" +
                "\t\t\t\"email\": null,\n" +
                "\t\t\t\"imei\": null,\n" +
                "\t\t\t\"imsi\": null,\n" +
                "\t\t\t\"phoneBrand\": null,\n" +
                "\t\t\t\"phoneModel\": null,\n" +
                "\t\t\t\"phoneOs\": null,\n" +
                "\t\t\t\"appVersion\": null,\n" +
                "\t\t\t\"score\": null,\n" +
                "\t\t\t\"lifeRadius\": null,\n" +
                "\t\t\t\"lifeRadiusName\": null,\n" +
                "\t\t\t\"idcardPhoto\": null,\n" +
                "\t\t\t\"user\": null,\n" +
                "\t\t\t\"channelCode\": \"g001\",\n" +
                "\t\t\t\"channelStr\": null,\n" +
                "\t\t\t\"notInfoStates\": null,\n" +
                "\t\t\t\"idcardAuthen\": null,\n" +
                "\t\t\t\"contract\": null,\n" +
                "\t\t\t\"amount\": null,\n" +
                "\t\t\t\"expireRepay\": null,\n" +
                "\t\t\t\"recentRepay\": null,\n" +
                "\t\t\t\"idcardFaceCertConfidence\": null,\n" +
                "\t\t\t\"attachmentId\": null,\n" +
                "\t\t\t\"totalBalance\": null,\n" +
                "\t\t\t\"infoView\": null,\n" +
                "\t\t\t\"isSync\": null,\n" +
                "\t\t\t\"classification\": null,\n" +
                "\t\t\t\"hackAuthen\": null,\n" +
                "\t\t\t\"recommendCode\": null,\n" +
                "\t\t\t\"recommendMsg\": null,\n" +
                "\t\t\t\"remark\": null,\n" +
                "\t\t\t\"applyScore\": null,\n" +
                "\t\t\t\"applyClass\": null,\n" +
                "\t\t\t\"handmade\": null,\n" +
                "\t\t\t\"suspicious\": null,\n" +
                "\t\t\t\"currentQuota\": null,\n" +
                "\t\t\t\"xshdBlack\": null,\n" +
                "\t\t\t\"userBanks\": null,\n" +
                "\t\t\t\"hxUserId\": null,\n" +
                "\t\t\t\"inviteUserInfo\": null,\n" +
                "\t\t\t\"loanSuccessTimes\": null,\n" +
                "\t\t\t\"idcardTimeLimit\": null\n" +
                "\t\t},\n" +
                "\t\t\"repayList \": [{\n" +
                "\t\t\t\t\"amount\": 1000.00,\n" +
                "\t\t\t\t\"promiseDate\": \"2016-05-19\",\n" +
                "\t\t\t\t\"overdueFine\": 8.77,\n" +
                "\t\t\t\t\"repayTotal\": 395.77,\n" +
                "\t\t\t\t\"principal\": 327.00,\n" +
                "\t\t\t\t\"overdue\": 3,\n" +
                "\t\t\t\t\"terms\": 1,\n" +
                "\t\t\t\t\"interest\": 20.00,\n" +
                "\t\t\t\t\"isSupportHaixiang\": 0,\n" +
                "\t\t\t\t\"realReapyMoney\": 395.77,\n" +
                "\t\t\t\t\"term\": 1,\n" +
                "\t\t\t\t\"id\": 78,\n" +
                "\t\t\t\t\"state\": \"1\",\n" +
                "\t\t\t\t\"monthFee\": 40.0000,\n" +
                "\t\t\t\t\"loanId\": 1293375,\n" +
                "\t\t\t\t\"overdueStatus\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"amount\": 1000.00,\n" +
                "\t\t\t\t\"promiseDate\": \"2016-06-19\",\n" +
                "\t\t\t\t\"overdueFine\": 1166.37,\n" +
                "\t\t\t\t\"repayTotal\": 1553.37,\n" +
                "\t\t\t\t\"principal\": 333.54,\n" +
                "\t\t\t\t\"overdue\": 186,\n" +
                "\t\t\t\t\"terms\": 2,\n" +
                "\t\t\t\t\"interest\": 13.46,\n" +
                "\t\t\t\t\"isSupportHaixiang\": 0,\n" +
                "\t\t\t\t\"realReapyMoney\": 1553.37,\n" +
                "\t\t\t\t\"term\": 1,\n" +
                "\t\t\t\t\"id\": 79,\n" +
                "\t\t\t\t\"state\": \"1\",\n" +
                "\t\t\t\t\"monthFee\": 40.0000,\n" +
                "\t\t\t\t\"loanId\": 1293375,\n" +
                "\t\t\t\t\"overdueStatus\": 0\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"amount\": 1000.00,\n" +
                "\t\t\t\t\"promiseDate\": \"2016-07-19\",\n" +
                "\t\t\t\t\"overdueFine\": 39093.30,\n" +
                "\t\t\t\t\"repayTotal\": 39480.30,\n" +
                "\t\t\t\t\"principal\": 340.21,\n" +
                "\t\t\t\t\"overdue\": 619,\n" +
                "\t\t\t\t\"terms\": 3,\n" +
                "\t\t\t\t\"interest\": 6.79,\n" +
                "\t\t\t\t\"isSupportHaixiang\": 0,\n" +
                "\t\t\t\t\"term\": 1,\n" +
                "\t\t\t\t\"id\": 80,\n" +
                "\t\t\t\t\"state\": \"0\",\n" +
                "\t\t\t\t\"monthFee\": 40.0000,\n" +
                "\t\t\t\t\"loanId\": 1293375,\n" +
                "\t\t\t\t\"overdueStatus\": 0\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";
        Json2MapUtils json2MapUtils = new Json2MapUtils();
        Map result = json2MapUtils.json2Map(js);
        System.out.println(result.size());
        System.out.println(result);
    }

    @Test
    public void jsonParase(){
        String s= "{\n" +
                "    \"report\": [\n" +
                "        {\n" +
                "            \"key\": \"data_type\",\n" +
                "            \"value\": \"运营商\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"source_name\",\n" +
                "            \"value\": \"chinamobilezj\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"source_name_zh\",\n" +
                "            \"value\": \"浙江移动\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"data_gain_time\",\n" +
                "            \"value\": \"2017-10-13\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"task_id\",\n" +
                "            \"value\": \"78c15210-afbb-11e7-8963-00163e0e0050\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"update_time\",\n" +
                "            \"value\": \"2017-10-13 10:08:57\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"version\",\n" +
                "            \"value\": \"1.0\"\n" +
                "        }\n" +
                "    ],\n" +
                "\"call_time_detail\": [\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"642\",\n" +
                "                \"item_3m\": \"1825\",\n" +
                "                \"item_6m\": \"2243\",\n" +
                "                \"avg_item_3m\": null,\n" +
                "                \"avg_item_6m\": null\n" +
                "            },\n" +
                "            \"app_point\": \"max_single_time\",\n" +
                "            \"app_point_zh\": \"单次通话最长时长（秒）\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"1\",\n" +
                "                \"item_3m\": \"1\",\n" +
                "                \"item_6m\": \"1\",\n" +
                "                \"avg_item_3m\": null,\n" +
                "                \"avg_item_6m\": null\n" +
                "            },\n" +
                "            \"app_point\": \"min_single_time\",\n" +
                "            \"app_point_zh\": \"单次通话最短时长（秒）\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"77\",\n" +
                "                \"item_3m\": \"233\",\n" +
                "                \"item_6m\": \"499\",\n" +
                "                \"avg_item_3m\": \"77.67\",\n" +
                "                \"avg_item_6m\": \"83.17\"\n" +
                "            },\n" +
                "            \"app_point\": \"cnt_1min_within\",\n" +
                "            \"app_point_zh\": \"时长在1min内的通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"8\",\n" +
                "                \"item_3m\": \"65\",\n" +
                "                \"item_6m\": \"118\",\n" +
                "                \"avg_item_3m\": \"21.67\",\n" +
                "                \"avg_item_6m\": \"19.67\"\n" +
                "            },\n" +
                "            \"app_point\": \"cnt_1min_5min\",\n" +
                "            \"app_point_zh\": \"时长在1min-5min内的通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"8\",\n" +
                "                \"item_3m\": \"20\",\n" +
                "                \"item_6m\": \"26\",\n" +
                "                \"avg_item_3m\": \"6.67\",\n" +
                "                \"avg_item_6m\": \"4.33\"\n" +
                "            },\n" +
                "            \"app_point\": \"cnt_5min_10min\",\n" +
                "            \"app_point_zh\": \"时长在5min-10min内的通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"1\",\n" +
                "                \"item_3m\": \"8\",\n" +
                "                \"item_6m\": \"16\",\n" +
                "                \"avg_item_3m\": \"2.67\",\n" +
                "                \"avg_item_6m\": \"2.67\"\n" +
                "            },\n" +
                "            \"app_point\": \"cnt_10min_over\",\n" +
                "            \"app_point_zh\": \"时长在10min以上的通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"93\",\n" +
                "                \"item_3m\": \"324\",\n" +
                "                \"item_6m\": \"657\",\n" +
                "                \"avg_item_3m\": \"108.00\",\n" +
                "                \"avg_item_6m\": \"109.50\"\n" +
                "            },\n" +
                "            \"app_point\": \"daytime_cnt\",\n" +
                "            \"app_point_zh\": \"白天(7:00-0:00)通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"1\",\n" +
                "                \"item_3m\": \"2\",\n" +
                "                \"item_6m\": \"2\",\n" +
                "                \"avg_item_3m\": \"0.67\",\n" +
                "                \"avg_item_6m\": \"0.33\"\n" +
                "            },\n" +
                "            \"app_point\": \"night_cnt\",\n" +
                "            \"app_point_zh\": \"夜晚(0:00-7:00)通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"6567\",\n" +
                "                \"item_3m\": \"31690\",\n" +
                "                \"item_6m\": \"55397\",\n" +
                "                \"avg_item_3m\": \"10563\",\n" +
                "                \"avg_item_6m\": \"9233\"\n" +
                "            },\n" +
                "            \"app_point\": \"daytime_time\",\n" +
                "            \"app_point_zh\": \"白天(7:00-0:00)通话时长（秒）\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"5\",\n" +
                "                \"item_3m\": \"58\",\n" +
                "                \"item_6m\": \"58\",\n" +
                "                \"avg_item_3m\": \"19\",\n" +
                "                \"avg_item_6m\": \"10\"\n" +
                "            },\n" +
                "            \"app_point\": \"night_time\",\n" +
                "            \"app_point_zh\": \"夜晚(0:00-7:00)通话时长（秒）\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"2\",\n" +
                "                \"item_3m\": \"2\",\n" +
                "                \"item_6m\": \"99\",\n" +
                "                \"avg_item_3m\": \"0.67\",\n" +
                "                \"avg_item_6m\": \"16.50\"\n" +
                "            },\n" +
                "            \"app_point\": \"local_cnt\",\n" +
                "            \"app_point_zh\": \"本机号码归属地通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"92\",\n" +
                "                \"item_3m\": \"324\",\n" +
                "                \"item_6m\": \"560\",\n" +
                "                \"avg_item_3m\": \"108.00\",\n" +
                "                \"avg_item_6m\": \"93.33\"\n" +
                "            },\n" +
                "            \"app_point\": \"remote_cnt\",\n" +
                "            \"app_point_zh\": \"本机号码归属地以外通话次数\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"12\",\n" +
                "                \"item_3m\": \"12\",\n" +
                "                \"item_6m\": \"4331\",\n" +
                "                \"avg_item_3m\": \"4\",\n" +
                "                \"avg_item_6m\": \"722\"\n" +
                "            },\n" +
                "            \"app_point\": \"local_time\",\n" +
                "            \"app_point_zh\": \"本机号码归属地通话时长（秒）\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"item\": {\n" +
                "                \"item_1m\": \"6560\",\n" +
                "                \"item_3m\": \"31736\",\n" +
                "                \"item_6m\": \"51124\",\n" +
                "                \"avg_item_3m\": \"10579\",\n" +
                "                \"avg_item_6m\": \"8521\"\n" +
                "            },\n" +
                "            \"app_point\": \"remote_time\",\n" +
                "            \"app_point_zh\": \"本机号码归属地以外通话时长（秒）\"\n" +
                "        }\n" +
                "    ]," +
                " \"basic_check_items\": [\n" +
                "        {\n" +
                "            \"result\": \"无数据\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"idcard_check\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"未提供邮箱\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"email_check\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"未提供通讯地址\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"address_check\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"通话记录正常\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"call_data_check\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"运营商未提供身份证\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"idcard_match\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"匹配失败\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"name_match\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"否\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"is_name_and_idcard_in_court_black\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"否\",\n" +
                "            \"comment\": \"\",\n" +
                "            \"check_item\": \"is_name_and_idcard_in_finance_black\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"否\",\n" +
                "            \"comment\": \"\",\n" +
                "            \"check_item\": \"is_name_and_mobile_in_finance_black\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"3\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"mobile_silence_3m\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"3\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"mobile_silence_6m\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"0\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"arrearage_risk_3m\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"0\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"arrearage_risk_6m\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"result\": \"0\",\n" +
                "            \"comment\": null,\n" +
                "            \"check_item\": \"binding_risk\"\n" +
                "        }\n" +
                "    ]," +
                "    \"user_basic\": [\n" +
                "        {\n" +
                "            \"key\": \"name\",\n" +
                "            \"value\": \"张三\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"id_card\",\n" +
                "            \"value\": \"371521199305281420\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"gender\",\n" +
                "            \"value\": \"男\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"age\",\n" +
                "            \"value\": \"24\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"constellation\",\n" +
                "            \"value\": \"双子座\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"province\",\n" +
                "            \"value\": \"山东省\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"city\",\n" +
                "            \"value\": \"聊城市\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"region\",\n" +
                "            \"value\": \"阳谷县\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"native_place\",\n" +
                "            \"value\": \"山东省聊城市阳谷县\"\n" +
                "        }\n" +
                "    ],\n" +
                " \"friend_circle\": {\n" +
                "        \"summary\": [\n" +
                "            {\n" +
                "                \"key\": \"friend_num_3m\",\n" +
                "                \"value\": \"183\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"good_friend_num_3m\",\n" +
                "                \"value\": \"2\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"friend_city_center_3m\",\n" +
                "                \"value\": \"杭州\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"is_city_match_friend_city_center_3m\",\n" +
                "                \"value\": \"否\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"inter_peer_num_3m\",\n" +
                "                \"value\": \"22\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"friend_num_6m\",\n" +
                "                \"value\": \"384\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"good_friend_num_6m\",\n" +
                "                \"value\": \"4\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"friend_city_center_6m\",\n" +
                "                \"value\": \"杭州\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"is_city_match_friend_city_center_6m\",\n" +
                "                \"value\": \"否\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"inter_peer_num_6m\",\n" +
                "                \"value\": \"49\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"peer_num_top_list\": [\n" +
                "            {\n" +
                "                \"key\": \"peer_num_top3_3m\",\n" +
                "                \"top_item\": [\n" +
                "                    {\n" +
                "                        \"peer_number\": \"13101111111\",\n" +
                "                        \"peer_num_loc\": \"贵阳\",\n" +
                "                        \"group_name\": \"未知\",\n" +
                "                        \"company_name\": \"未知\",\n" +
                "                        \"call_cnt\": 35,\n" +
                "                        \"call_time\": 5582,\n" +
                "                        \"dial_cnt\": 12,\n" +
                "                        \"dialed_cnt\": 23,\n" +
                "                        \"dial_time\": 1755,\n" +
                "                        \"dialed_time\": 3827\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"peer_number\": \"13102222222\",\n" +
                "                        \"peer_num_loc\": \"温州\",\n" +
                "                        \"group_name\": \"未知\",\n" +
                "                        \"company_name\": \"未知\",\n" +
                "                        \"call_cnt\": 20,\n" +
                "                        \"call_time\": 2252,\n" +
                "                        \"dial_cnt\": 19,\n" +
                "                        \"dialed_cnt\": 1,\n" +
                "                        \"dial_time\": 2210,\n" +
                "                        \"dialed_time\": 42\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"peer_number\": \"13103333333\",\n" +
                "                        \"peer_num_loc\": \"宁波\",\n" +
                "                        \"group_name\": \"未知\",\n" +
                "                        \"company_name\": \"未知\",\n" +
                "                        \"call_cnt\": 10,\n" +
                "                        \"call_time\": 522,\n" +
                "                        \"dial_cnt\": 5,\n" +
                "                        \"dialed_cnt\": 5,\n" +
                "                        \"dial_time\": 251,\n" +
                "                        \"dialed_time\": 271\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"peer_num_top3_6m\",\n" +
                "                \"top_item\": [\n" +
                "                    {\n" +
                "                        \"peer_number\": \"13101111111\",\n" +
                "                        \"peer_num_loc\": \"温州\",\n" +
                "                        \"group_name\": \"未知\",\n" +
                "                        \"company_name\": \"未知\",\n" +
                "                        \"call_cnt\": 41,\n" +
                "                        \"call_time\": 3808,\n" +
                "                        \"dial_cnt\": 26,\n" +
                "                        \"dialed_cnt\": 15,\n" +
                "                        \"dial_time\": 2862,\n" +
                "                        \"dialed_time\": 946\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"peer_number\": \"13102222222\",\n" +
                "                        \"peer_num_loc\": \"贵阳\",\n" +
                "                        \"group_name\": \"未知\",\n" +
                "                        \"company_name\": \"未知\",\n" +
                "                        \"call_cnt\": 37,\n" +
                "                        \"call_time\": 6094,\n" +
                "                        \"dial_cnt\": 13,\n" +
                "                        \"dialed_cnt\": 24,\n" +
                "                        \"dial_time\": 2186,\n" +
                "                        \"dialed_time\": 3908\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"peer_number\": \"057110086\",\n" +
                "                        \"peer_num_loc\": \"杭州\",\n" +
                "                        \"group_name\": \"通信服务机构\",\n" +
                "                        \"company_name\": \"中国移动客服热线\",\n" +
                "                        \"call_cnt\": 12,\n" +
                "                        \"call_time\": 1320,\n" +
                "                        \"dial_cnt\": 12,\n" +
                "                        \"dialed_cnt\": 0,\n" +
                "                        \"dial_time\": 1320,\n" +
                "                        \"dialed_time\": 0\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"location_top_list\": [\n" +
                "            {\n" +
                "                \"key\": \"location_top3_3m\",\n" +
                "                \"top_item\": [\n" +
                "                    {\n" +
                "                        \"location\": \"杭州\",\n" +
                "                        \"peer_number_cnt\": 124,\n" +
                "                        \"call_cnt\": 173,\n" +
                "                        \"call_time\": 5373,\n" +
                "                        \"dial_cnt\": 56,\n" +
                "                        \"dialed_cnt\": 117,\n" +
                "                        \"dial_time\": 2880,\n" +
                "                        \"dialed_time\": 2493\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"location\": \"贵阳\",\n" +
                "                        \"peer_number_cnt\": 1,\n" +
                "                        \"call_cnt\": 35,\n" +
                "                        \"call_time\": 5582,\n" +
                "                        \"dial_cnt\": 12,\n" +
                "                        \"dialed_cnt\": 23,\n" +
                "                        \"dial_time\": 1755,\n" +
                "                        \"dialed_time\": 3827\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"location\": \"温州\",\n" +
                "                        \"peer_number_cnt\": 9,\n" +
                "                        \"call_cnt\": 35,\n" +
                "                        \"call_time\": 3178,\n" +
                "                        \"dial_cnt\": 29,\n" +
                "                        \"dialed_cnt\": 6,\n" +
                "                        \"dial_time\": 2971,\n" +
                "                        \"dialed_time\": 207\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"key\": \"location_top3_6m\",\n" +
                "                \"top_item\": [\n" +
                "                    {\n" +
                "                        \"location\": \"杭州\",\n" +
                "                        \"peer_number_cnt\": 235,\n" +
                "                        \"call_cnt\": 326,\n" +
                "                        \"call_time\": 11847,\n" +
                "                        \"dial_cnt\": 98,\n" +
                "                        \"dialed_cnt\": 228,\n" +
                "                        \"dial_time\": 5544,\n" +
                "                        \"dialed_time\": 6303\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"location\": \"绍兴\",\n" +
                "                        \"peer_number_cnt\": 57,\n" +
                "                        \"call_cnt\": 87,\n" +
                "                        \"call_time\": 2505,\n" +
                "                        \"dial_cnt\": 20,\n" +
                "                        \"dialed_cnt\": 67,\n" +
                "                        \"dial_time\": 653,\n" +
                "                        \"dialed_time\": 1852\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"location\": \"温州\",\n" +
                "                        \"peer_number_cnt\": 13,\n" +
                "                        \"call_cnt\": 64,\n" +
                "                        \"call_time\": 7531,\n" +
                "                        \"dial_cnt\": 40,\n" +
                "                        \"dialed_cnt\": 24,\n" +
                "                        \"dial_time\": 6264,\n" +
                "                        \"dialed_time\": 1267\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }," +
                "    \"cell_phone\": [\n" +
                "        {\n" +
                "            \"key\": \"mobile\",\n" +
                "            \"value\": \"13100000000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"carrier_name\",\n" +
                "            \"value\": \"李四\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"carrier_idcard\",\n" +
                "            \"value\": \"运营商未提供身份证\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"reg_time\",\n" +
                "            \"value\": \"2013-09-21 00:00:00\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"in_time\",\n" +
                "            \"value\": \"50\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"email\",\n" +
                "            \"value\": \"运营商未提供邮箱\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"address\",\n" +
                "            \"value\": \"运营商未提供通讯地址\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"reliability\",\n" +
                "            \"value\": \"实名认证\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"phone_attribution\",\n" +
                "            \"value\": \"浙江绍兴\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"live_address\",\n" +
                "            \"value\": \"杭州\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"available_balance\",\n" +
                "            \"value\": \"4286\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"package_name\",\n" +
                "            \"value\": \"4G飞享套餐\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"bill_certification_day\",\n" +
                "            \"value\": \"2017-10-13\"\n" +
                "        }\n" +
                "    ]" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(s);
//        System.out.println(jsonObject.get("call_contact_detail"));


        //近6月好朋友联系数量（联系10次以上）
        JSONObject friendCircle = JSONObject.parseObject(jsonObject.get("friend_circle").toString());
        JSONArray jsonCircle = friendCircle.getJSONArray("summary");

        for (int i = 0;i<jsonCircle.size();i++){
            JSONObject json = jsonCircle.getJSONObject(i);
            if (json.get("key").equals("good_friend_num_6m") && json.get("value") != null){
                System.out.println(json.get("key"));
                System.out.println(json.get("value"));
            }
        }

/*        //近6月 时长在10min以上的通话次数
        JSONArray basicCheckItems = JSONObject.parseArray(jsonObject.get("call_time_detail").toString());
        for (int i = 0;i<basicCheckItems.size();i++){
            JSONObject json = basicCheckItems.getJSONObject(i);
            if (json.get("app_point").equals("cnt_10min_over")){
                JSONObject jsonItem = json.getJSONObject("item");
                System.out.println(json.get("item"));
                System.out.println(jsonItem.get("item_6m"));
            }
        }*/
        /*//静默情况
        JSONArray basicCheckItems = JSONObject.parseArray(jsonObject.get("basic_check_items").toString());
        for (int i = 0;i<basicCheckItems.size();i++){
            JSONObject json = basicCheckItems.getJSONObject(i);
            if (json.get("check_item").equals("mobile_silence_6m")){
                System.out.println(json.get("result"));
            }
        }*/
        /*//开户信息，开户时长
        JSONArray cellPhone = JSONObject.parseArray(jsonObject.get("cell_phone").toString());
        for (int i = 0;i<cellPhone.size();i++){
            JSONObject json = cellPhone.getJSONObject(i);
            if (json.get("key").equals("reg_time")){

            }else if (json.get("key").equals("in_time")){

            }
            System.out.println(json.get("key"));
            System.out.println(json.get("value"));
        }*/
        /*//近3月凌晨通话次数
        JSONArray callContactDetails = JSONObject.parseArray(jsonObject.get("call_contact_detail").toString());
        Map<String,Object> callContactMap;
        int callCntNight3m = 0;
        //近1月（最近30天）通话次数
        int callCnt1m = 0;
        //近6月（最近0-180天）通话次数
        int callCnt6m = 0;
        //近6月节假日通话次数
        int callCntHoliday6m = 0;
        //近6月工作日通话次数
        int callCntWeekday6m = 0;
        //近6月凌晨通话次数
        int callCntNight6m = 0;
        for (int i=0;i<callContactDetails.size();i++){
            callContactMap = (Map<String, Object>) callContactDetails.get(i);
            int init3m = (callContactMap.get("call_cnt_night_3m") == null)?0:(int)callContactMap.get("call_cnt_night_3m");
            callCntNight3m = callCntNight3m+init3m;
            int init1m = (callContactMap.get("call_cnt_1m") == null)?0:(int)callContactMap.get("call_cnt_1m");
            callCnt1m = callCnt1m+init1m;
            int init6m =(callContactMap.get("call_cnt_6m") == null)?0:(int)callContactMap.get("call_cnt_6m");
            callCnt6m =callCnt6m+init6m;
            int initHoliday6m =(callContactMap.get("call_cnt_holiday_6m") == null)?0:(int)callContactMap.get("call_cnt_holiday_6m");
            callCntHoliday6m =callCntHoliday6m+initHoliday6m;
            int initWeekday6m =(callContactMap.get("call_cnt_weekday_6m") == null)?0:(int)callContactMap.get("call_cnt_weekday_6m");
            callCntWeekday6m =callCntWeekday6m+initWeekday6m;
            int initNight6m =(callContactMap.get("call_cnt_night_6m") == null)?0:(int)callContactMap.get("call_cnt_night_6m");
            callCntNight6m =callCntNight6m+initNight6m;
        }
        System.out.println(callCntNight3m);
        System.out.println(callCnt1m);
        System.out.println(callCnt6m);
        System.out.println(callCntHoliday6m);
        System.out.println(callCntWeekday6m);
        System.out.println(callCntNight6m);
*/
    }

    @Test
    public void jsonParaseJuxinli(){
        String s="{\n" +
                "    \"note\":\"\",\n" +
                "    \"report_data\":{\n\"" +
                "user_info_check\":{\n" +
                "            \"check_black_info\":{\n" +
                "                \"contacts_class1_blacklist_cnt\":0,\n" +
                "                \"contacts_router_ratio\":0.28205128205128205,\n" +
                "                \"contacts_class2_blacklist_cnt\":35,\n" +
                "                \"contacts_router_cnt\":11,\n" +
                "                \"contacts_class1_cnt\":39,\n" +
                "                \"phone_gray_score\":61\n" +
                "            },\n" +
                "            \"check_search_info\":{\n" +
                "                \"arised_open_web\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"phone_with_other_idcards\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"idcard_with_other_phones\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"register_org_cnt\":0,\n" +
                "                \"idcard_with_other_names\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"searched_org_type\":[\n" +
                "                    \"线上信用卡代还\",\n" +
                "                    \"线上消费分期\",\n" +
                "                    \"线上微额快速贷\",\n" +
                "                    \"线上信用现金贷\"\n" +
                "                ],\n" +
                "                \"searched_org_cnt\":10,\n" +
                "                \"register_org_type\":[\n" +
                "\n" +
                "                ],\n" +
                "                \"phone_with_other_names\":[\n" +
                "\n" +
                "                ]\n" +
                "            }\n" +
                "        }," +
                "        \"behavior_check\":[\n" +
                "            {\n" +
                "                \"check_point\":\"regular_circle\",\n" +
                "                \"score\":0,\n" +
                "                \"check_point_cn\":\"朋友圈在哪里\",\n" +
                "                \"result\":\"无数据\",\n" +
                "                \"evidence\":\"未提供居住地址\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"phone_used_time\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"号码使用时间\",\n" +
                "                \"result\":\"长期使用(24个月以上，包含24)\",\n" +
                "                \"evidence\":\"根据号码[13430445413]运营商提供的认证时间,推算该号码使用92个月\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"phone_silent\",\n" +
                "                \"score\":2,\n" +
                "                \"check_point_cn\":\"手机静默情况(连续24小时内无通话记录计为静默一天)\",\n" +
                "                \"result\":\"159天内有122天无通话记录\",\n" +
                "                \"evidence\":\"根据运营商详单数据，连续三天以上无通话记录14次: 2019-02-05 - 2019-02-09, 5天 / 2019-02-18 - 2019-02-20, 3天 / 2019-03-03 - 2019-03-08, 6天 / 2019-03-13 - 2019-03-16, 4天 / 2019-03-20 - 2019-03-22, 3天 / 2019-03-24 - 2019-03-26, 3天 / 2019-04-02 - 2019-04-06, 5天 / 2019-04-22 - 2019-04-26, 5天 / 2019-04-28 - 2019-05-04, 7天 / 2019-05-14 - 2019-05-18, 5天 / 2019-05-20 - 2019-05-26, 7天 / 2019-05-29 - 2019-06-01, 4天 / 2019-06-03 - 2019-06-06, 4天 / 2019-06-30 - 2019-07-04, 5天\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_each_other\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"互通过电话的号码数量\",\n" +
                "                \"result\":\"数量正常(10-100)\",\n" +
                "                \"evidence\":\"互通过电话的号码有11个，比例为20.0%\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_macao\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"澳门电话通话情况\",\n" +
                "                \"result\":\"无通话记录\",\n" +
                "                \"evidence\":\"未发现澳门地区电话通话记录\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_110\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"110话通话情况\",\n" +
                "                \"result\":\"无通话记录\",\n" +
                "                \"evidence\":\"未发现与110电话通话记录\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_120\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"120话通话情况\",\n" +
                "                \"result\":\"无通话记录\",\n" +
                "                \"evidence\":\"未发现与120电话通话记录\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_lawyer\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"律师号码通话情况\",\n" +
                "                \"result\":\"无通话记录\",\n" +
                "                \"evidence\":\"未发现与律师电话通话记录\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_court\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"法院号码通话情况\",\n" +
                "                \"result\":\"无通话记录\",\n" +
                "                \"evidence\":\"未发现与法院电话通话记录\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"contact_night\",\n" +
                "                \"score\":1,\n" +
                "                \"check_point_cn\":\"夜间活动情况(23点-6点)\",\n" +
                "                \"result\":\"偶尔夜间活动(夜间通话比例20%-50%， 包含20%)\",\n" +
                "                \"evidence\":\"晚间活跃频率占全天的36.81%\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"ebusiness_info\",\n" +
                "                \"score\":0,\n" +
                "                \"check_point_cn\":\"总体电商使用情况\",\n" +
                "                \"result\":\"无数据\",\n" +
                "                \"evidence\":\"未提供电商数据\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_point\":\"person_addr_changed\",\n" +
                "                \"score\":0,\n" +
                "                \"check_point_cn\":\"申请人本人地址变化情况\",\n" +
                "                \"result\":\"无数据\",\n" +
                "                \"evidence\":\"未提供电商数据\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"collection_contact\":[\n" +
                "            {\n" +
                "                \"begin_date\":\"\",\n" +
                "                \"total_amount\":0,\n" +
                "                \"end_date\":\"\",\n" +
                "                \"total_count\":0,\n" +
                "                \"contact_details\":[\n" +
                "                    {\n" +
                "                        \"sms_cnt\":0,\n" +
                "                        \"phone_num\":\"17841122430\",\n" +
                "                        \"phone_num_loc\":\"广东\",\n" +
                "                        \"trans_start\":\"\",\n" +
                "                        \"call_in_cnt\":0,\n" +
                "                        \"call_cnt\":0,\n" +
                "                        \"call_len\":0,\n" +
                "                        \"call_out_cnt\":0,\n" +
                "                        \"trans_end\":\"\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"contact_name\":\"赖明哲\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"begin_date\":\"\",\n" +
                "                \"total_amount\":0,\n" +
                "                \"end_date\":\"\",\n" +
                "                \"total_count\":0,\n" +
                "                \"contact_details\":[\n" +
                "                    {\n" +
                "                        \"sms_cnt\":0,\n" +
                "                        \"phone_num\":\"13651455834\",\n" +
                "                        \"phone_num_loc\":\"广东\",\n" +
                "                        \"trans_start\":\"\",\n" +
                "                        \"call_in_cnt\":0,\n" +
                "                        \"call_cnt\":0,\n" +
                "                        \"call_len\":0,\n" +
                "                        \"call_out_cnt\":0,\n" +
                "                        \"trans_end\":\"\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"contact_name\":\"占爱明\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"contact_list\":[\n" +
                "            {\n" +
                "                \"contact_noon\":0,\n" +
                "                \"phone_num_loc\":\"柬埔寨\",\n" +
                "                \"contact_3m\":0,\n" +
                "                \"contact_1m\":0,\n" +
                "                \"contact_1w\":0,\n" +
                "                \"p_relation\":\"\",\n" +
                "                \"phone_num\":\"00855974468016\",\n" +
                "                \"contact_name\":\"未知\",\n" +
                "                \"call_in_cnt\":1,\n" +
                "                \"call_out_cnt\":0,\n" +
                "                \"call_out_len\":0,\n" +
                "                \"contact_holiday\":0,\n" +
                "                \"needs_type\":\"未知\",\n" +
                "                \"contact_weekday\":0,\n" +
                "                \"contact_afternoon\":1,\n" +
                "                \"call_len\":1.2166666666666666,\n" +
                "                \"contact_early_morning\":0,\n" +
                "                \"contact_night\":0,\n" +
                "                \"contact_3m_plus\":1,\n" +
                "                \"call_cnt\":1,\n" +
                "                \"call_in_len\":1.2166666666666666,\n" +
                "                \"contact_all_day\":false,\n" +
                "                \"contact_morning\":0,\n" +
                "                \"contact_weekend\":1\n" +
                "            },\n" +
                "            {\n" +
                "                \"contact_noon\":0,\n" +
                "                \"phone_num_loc\":\"全国\",\n" +
                "                \"contact_3m\":1,\n" +
                "                \"contact_1m\":0,\n" +
                "                \"contact_1w\":0,\n" +
                "                \"p_relation\":\"\",\n" +
                "                \"phone_num\":\"95559\",\n" +
                "                \"contact_name\":\"交通银行客服电话\",\n" +
                "                \"call_in_cnt\":1,\n" +
                "                \"call_out_cnt\":0,\n" +
                "                \"call_out_len\":0,\n" +
                "                \"contact_holiday\":0,\n" +
                "                \"needs_type\":\"银行\",\n" +
                "                \"contact_weekday\":1,\n" +
                "                \"contact_afternoon\":0,\n" +
                "                \"call_len\":0.3333333333333333,\n" +
                "                \"contact_early_morning\":0,\n" +
                "                \"contact_night\":0,\n" +
                "                \"contact_3m_plus\":0,\n" +
                "                \"call_cnt\":1,\n" +
                "                \"call_in_len\":0.3333333333333333,\n" +
                "                \"contact_all_day\":false,\n" +
                "                \"contact_morning\":1,\n" +
                "                \"contact_weekend\":0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ebusiness_expense\":[\n" +
                "\n" +
                "        ],\n" +
                "        \"contact_region\":[\n" +
                "            {\n" +
                "                \"region_loc\":\"上海\",\n" +
                "                \"region_uniq_num_cnt\":1,\n" +
                "                \"region_call_out_time\":0,\n" +
                "                \"region_avg_call_in_time\":2.05,\n" +
                "                \"region_call_in_time\":2.05,\n" +
                "                \"region_call_out_cnt\":0,\n" +
                "                \"region_avg_call_out_time\":0,\n" +
                "                \"region_call_in_cnt_pct\":0.013888888888888888,\n" +
                "                \"region_call_in_time_pct\":0.03411927877947295,\n" +
                "                \"region_call_in_cnt\":1,\n" +
                "                \"region_call_out_time_pct\":0,\n" +
                "                \"region_call_out_cnt_pct\":0\n" +
                "            },\n" +
                "            {\n" +
                "                \"region_loc\":\"运营商\",\n" +
                "                \"region_uniq_num_cnt\":1,\n" +
                "                \"region_call_out_time\":8.333333333333334,\n" +
                "                \"region_avg_call_in_time\":0,\n" +
                "                \"region_call_in_time\":0,\n" +
                "                \"region_call_out_cnt\":6,\n" +
                "                \"region_avg_call_out_time\":1.388888888888889,\n" +
                "                \"region_call_in_cnt_pct\":0,\n" +
                "                \"region_call_in_time_pct\":0,\n" +
                "                \"region_call_in_cnt\":0,\n" +
                "                \"region_call_out_time_pct\":0.084774499830451,\n" +
                "                \"region_call_out_cnt_pct\":0.07407407407407407\n" +
                "            }\n" +
                "        ],\n" +
                "        \"application_check\":[\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"key_value\":\"占磊\"\n" +
                "                },\n" +
                "                \"app_point\":\"user_name\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"province\":\"湖北省\",\n" +
                "                    \"city\":\"襄阳市\",\n" +
                "                    \"key_value\":\"420625199211193017\",\n" +
                "                    \"gender\":\"男\",\n" +
                "                    \"age\":26,\n" +
                "                    \"financial_blacklist\":{\n" +
                "                        \"arised\":false,\n" +
                "                        \"black_type\":[\n" +
                "\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"court_blacklist\":{\n" +
                "                        \"arised\":false,\n" +
                "                        \"black_type\":[\n" +
                "\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"region\":\"谷城县\"\n" +
                "                },\n" +
                "                \"app_point\":\"id_card\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"website\":\"广东移动\",\n" +
                "                    \"check_ebusiness\":\"无法判断该号码的电商使用情况(无电商数据)\",\n" +
                "                    \"key_value\":\"13430445413\",\n" +
                "                    \"check_name\":\"用户姓名与运营商提供的姓名[*磊]匹配成功\",\n" +
                "                    \"reg_time\":\"2011-12-14 17:21:13\",\n" +
                "                    \"financial_blacklist\":{\n" +
                "                        \"arised\":false,\n" +
                "                        \"black_type\":[\n" +
                "\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"reliability\":\"实名认证\",\n" +
                "                    \"check_idcard\":\"运营商未提供身份证号码\"\n" +
                "                },\n" +
                "                \"app_point\":\"cell_phone\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"check_ebusiness\":\"无法判断该居住地址的电商使用情况(无电商数据)\",\n" +
                "                    \"key_value\":\"\",\n" +
                "                    \"check_addr\":\"无法定位居住地址(未提供居住地址)\"\n" +
                "                },\n" +
                "                \"app_point\":\"home_addr\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"check_mobile\":\"无法判断该家庭电话的通话情况(无家庭电话)\",\n" +
                "                    \"key_value\":\"\"\n" +
                "                },\n" +
                "                \"app_point\":\"home_phone\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"check_mobile\":\"没有该联系人电话的通话记录\",\n" +
                "                    \"key_value\":\"13651455834\",\n" +
                "                    \"contact_name\":\"占爱明\",\n" +
                "                    \"relationship\":\"父母\",\n" +
                "                    \"check_xiaohao\":\"该联系人号码非临时小号\"\n" +
                "                },\n" +
                "                \"app_point\":\"contact\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"check_points\":{\n" +
                "                    \"check_mobile\":\"没有该联系人电话的通话记录\",\n" +
                "                    \"key_value\":\"17841122430\",\n" +
                "                    \"contact_name\":\"赖明哲\",\n" +
                "                    \"relationship\":\"朋友\",\n" +
                "                    \"check_xiaohao\":\"该联系人号码非临时小号\"\n" +
                "                },\n" +
                "                \"app_point\":\"contact\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"deliver_address\":[\n" +
                "\n" +
                "        ],\n" +
                "        \"report\":{\n" +
                "            \"rpt_id\":\"201907111540130002\",\n" +
                "            \"token\":\"10bb59deef8e4bf6a58dff8c49323f71\",\n" +
                "            \"version\":\"4.2\",\n" +
                "            \"update_time\":\"2019-07-11T07:40:13.000Z\"\n" +
                "        },\n" +
                "        \"trip_info\":[\n" +
                "\n" +
                "        ],\n" +
                "        \"_id\":\"5d26e7df7ac7611dc01f58d2\",\n" +
                "        \"cell_behavior\":[\n" +
                "            {\n" +
                "                \"phone_num\":\"13430445413\",\n" +
                "                \"behavior\":[\n" +
                "                    {\n" +
                "                        \"sms_cnt\":1,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":1563.99609375,\n" +
                "                        \"total_amount\":0,\n" +
                "                        \"call_out_time\":3.533333333333333,\n" +
                "                        \"cell_mth\":\"2019-07\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":13,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":3,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":5.9,\n" +
                "                        \"call_in_cnt\":10\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"sms_cnt\":10,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":9357.513671875,\n" +
                "                        \"total_amount\":88.2,\n" +
                "                        \"call_out_time\":46.25,\n" +
                "                        \"cell_mth\":\"2019-06\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":39,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":25,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":14.85,\n" +
                "                        \"call_in_cnt\":14\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"sms_cnt\":5,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":9074.232421875,\n" +
                "                        \"total_amount\":88,\n" +
                "                        \"call_out_time\":14.066666666666666,\n" +
                "                        \"cell_mth\":\"2019-05\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":31,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":17,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":15.816666666666666,\n" +
                "                        \"call_in_cnt\":14\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"sms_cnt\":12,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":6478.2275390625,\n" +
                "                        \"total_amount\":88.1,\n" +
                "                        \"call_out_time\":5.183333333333334,\n" +
                "                        \"cell_mth\":\"2019-04\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":23,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":9,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":6.516666666666667,\n" +
                "                        \"call_in_cnt\":14\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"sms_cnt\":5,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":8062.5791015625,\n" +
                "                        \"total_amount\":88,\n" +
                "                        \"call_out_time\":16.45,\n" +
                "                        \"cell_mth\":\"2019-03\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":20,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":11,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":9.55,\n" +
                "                        \"call_in_cnt\":9\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"sms_cnt\":4,\n" +
                "                        \"cell_phone_num\":\"13430445413\",\n" +
                "                        \"net_flow\":14036.7431640625,\n" +
                "                        \"total_amount\":88,\n" +
                "                        \"call_out_time\":12.816666666666666,\n" +
                "                        \"cell_mth\":\"2019-02\",\n" +
                "                        \"cell_loc\":\"广东\",\n" +
                "                        \"call_cnt\":27,\n" +
                "                        \"cell_operator_zh\":\"广东移动\",\n" +
                "                        \"call_out_cnt\":16,\n" +
                "                        \"cell_operator\":\"chinamobilegd\",\n" +
                "                        \"call_in_time\":7.45,\n" +
                "                        \"call_in_cnt\":11\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"success\":\"true\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject reportData = jsonObject.getJSONObject("report_data");
        JSONArray behaviorCheck = reportData.getJSONArray("behavior_check");
        JSONArray applicationCheck = reportData.getJSONArray("application_check");
        Map<String,Object> behaviorMaps;
        Map<String,Object> applicationMaps;
        String checkPoint;
        String appPoint;

        JSONObject userInfoCheck = reportData.getJSONObject("user_info_check");
        JSONObject checkBlackInfo = userInfoCheck.getJSONObject("check_black_info");
        JSONObject checkSearchInfo = userInfoCheck.getJSONObject("check_search_info");

        System.out.println(checkBlackInfo.get("contacts_class1_cnt").toString());
        System.out.println(checkSearchInfo.get("searched_org_cnt").toString());


       /* for (int i=0;i<applicationCheck.size();i++){
            applicationMaps = (Map<String, Object>)applicationCheck.get(i);
            appPoint = applicationMaps.get("app_point").toString();
            JSONObject cellPhone = JSONObject.parseObject(applicationMaps.get("check_points").toString());
            if (appPoint.equals("cell_phone")){
                System.out.println(cellPhone.get("reg_time").toString());
            }
        }*/
/*
        for (int i=0;i<behaviorCheck.size();i++){
            behaviorMaps = (Map<String, Object>)behaviorCheck.get(i);
            checkPoint = behaviorMaps.get("check_point").toString();
            if (checkPoint.equals("phone_used_time")){
                System.out.println(behaviorMaps.get("evidence").toString());
            }
            if (checkPoint.equals("phone_silent")){
                System.out.println(behaviorMaps.get("evidence").toString());
            }
            if (checkPoint.equals("contact_each_other")){
                System.out.println(behaviorMaps.get("evidence").toString());
            }
            if (checkPoint.equals("contact_night")){
                System.out.println(behaviorMaps.get("evidence").toString());
            }
            if (checkPoint.equals("ebusiness_info")){
                System.out.println(behaviorMaps.get("evidence").toString());
            }
        }*/
//        System.out.println(behaviorCheck);

    }
}
