package utils;


import java.net.URLEncoder;
import java.util.*;

/**
 * @author MaFuxin
 * @Description:签名工具类
 * @date 2017/8/8 17:09
 */
public class SignUtils {
    /**
     * 获取签名字符串
     * @param parameters 待签名内容
     * @return 签名串
     * @throws Exception
     */
    public static String sign(Map<String, String> parameters, String key) {
        List<Map.Entry<String, String>> list = new ArrayList<>(parameters.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序
        list.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : list) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        // 在签名原始串后加上商户通信密钥的内容,进行 MD5 运算
        sb.append("key=" + key);
        String sign = MD5.MD5Encode(sb.toString());
        // 签名结果转为大写
        return sign.toUpperCase();
    }

    public static String sign(Map<String, String> parameters, String key, boolean signToLower) {
        List<Map.Entry<String, String>> list = new ArrayList<>(parameters.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序
        list.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : list) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        // 在签名原始串后加上商户通信密钥的内容,进行 MD5 运算
        sb.append("key=").append(key);
        String sign = MD5.MD5Encode(sb.toString());
        // 签名结果转为大写
        if (signToLower) {
            return sign.toLowerCase();
        } else {
            return sign.toUpperCase();
        }
    }

    /**
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串
     * @param parameters 要排序的Map对象
     * @param key 签名key
     * @param signToLower 是否需要将sign值转为小写
     * @return
     */
    public static String formatUrlMap(Map<String, String> parameters, String key,
            boolean signToLower) {
        List<Map.Entry<String, String>> list = new ArrayList<>(parameters.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序
        list.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : list) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        String paramStr = sb.toString();
        // 在签名原始串后加上商户通信密钥的内容,进行 MD5 运算
        sb.append("key=").append(key);
        String sign = MD5.MD5Encode(sb.toString());

        if (signToLower) {
            sign = sign.toLowerCase();
        } else {
            sign = sign.toUpperCase();
        }
        return paramStr + "&sign=" + sign;
    }

    /**
     * 验签
     * @param params
     * @param key
     * @return
     */
    public static boolean checkSign(Map<String, String> params, String key) {
        boolean result = false;
        if (params.containsKey("sign")) {
            String sign = params.get("sign");
            params.remove("sign");
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
            SignUtils.buildPayParams(buf, params, false);
            String preStr = buf.toString();
            String signRecieve = MD5.MD5Encode(preStr + "&key=" + key);
            result = sign.equalsIgnoreCase(signRecieve);
            params.put("sign", sign);
        }
        return result;
    }

    private static void buildPayParams(StringBuilder sb,Map<String, String> payParams,boolean encoding){
        List<String> keys = new ArrayList<String>(payParams.keySet());
        Collections.sort(keys);
        for(String key : keys){
            String value = payParams.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equals("aliNo")) {
                continue;
            }
            sb.append(key).append("=");
            if(encoding){
                sb.append(urlEncode(payParams.get(key)));
            }else{
                sb.append(payParams.get(key));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
    }

    private static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }

}
