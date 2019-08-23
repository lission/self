package utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author L-liang
 * @ClassName: HttpUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年10月28日 下午9:48:28
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger("log.http.HttpUtils");
    /**
     * @Fields ENCODING : 编码格式。发送编码格式统一用UTF-8
     */
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    private static Gson gson = new Gson();

    /**
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return String 返回类型
     * @Title: sentPost
     * @Description: post请求
     */
    public static String post(String url, Map<String, String> paramsMap) {

        String uuid = UUID.randomUUID().toString();
        logger.info("HttpUtils.post before uuid={},url={},params={}", uuid, url, paramsMap);
        String responseText = "";
        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, DEF_CHATSET));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
            logger.info("HttpUtils.post after uuid={},result={}", uuid, responseText);
        } catch (Exception e) {
            logger.error("HttpUtils.post Exception uuid={}", uuid, e);
        } finally {
            if (responseText.indexOf("未找到匹配的模板") != -1) {
                logger.debug("http-POST请求：url={" + url + "} , result={" + responseText + "}");
                logger.warn("未找到模板的参数:" + paramsMap);
            }
            try {
                response.close();
            } catch (Exception e) {
                logger.error("response关闭出错", e);
            }
        }
        return responseText;
    }

    /**
     * @return GY
     * 2017年8月17日
     * miniService通讯post
     */
    public static String postMiniService(String url, Map<String, String> paramsMap) {
        String responseText = "";
        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, DEF_CHATSET));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("【请求miniService异常！！】");
        } finally {
            if (responseText.indexOf("未找到匹配的模板") != -1) {
                logger.debug("http-POST请求：url={" + url + "} , result={" + responseText + "}");
                logger.warn("未找到模板的参数:" + paramsMap);
            }
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                logger.error("response关闭出错", e);
            }
        }
        return responseText;
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     */
    public static String net(String strUrl, Map params, String method) throws Exception {
        String uuid = UUID.randomUUID().toString();
        logger.info("HttpUtils.net before uuid={},url={},method={},params={}", uuid, strUrl, method, params);

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                if (params != null && !params.isEmpty()) {
                    strUrl = strUrl + "?" + urlencode(params);
                }
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            logger.info("HttpUtils.net after uuid={},result={}", uuid, rs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    // 将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * @param strUrl        请求地址
     * @param params        参数
     * @param method        请求方法，GET|POST|...
     * @param paramEncoding 参数编码方式 ，UTF-8|GB2312|...
     * @author liaowei
     * @date 2017年3月13日 上午11:34:48
     */
    public static String net(String strUrl, Map params, String method, String paramEncoding) throws Exception {
        String uuid = UUID.randomUUID().toString();
        logger.info("HttpUtils.net2 before uuid={},url={},method={},params={},encoding={}", uuid, strUrl, method, params, paramEncoding);
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                if (params != null && !params.isEmpty()) {
                    strUrl = strUrl + "?" + urlencode(params, paramEncoding);
                }
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params, paramEncoding));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            logger.info("HttpUtils.net2 after uuid={},result={}", uuid, rs);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            logger.error("requestSjmhPostException");
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    // 将map型转为请求参数型
    public static String urlencode(Map<String, String> data, String paramEncoding) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", paramEncoding)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

}
