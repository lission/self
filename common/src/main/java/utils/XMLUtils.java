package utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.*;

/**
 * @author lission
 * @Description:XML工具类
 * @date 2017/8/8 17:10
 */
public class XMLUtils {
    private static final Logger logger = LoggerFactory.getLogger(XMLUtils.class);

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param parameters
     * @return
     */
    public static String parseXML(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"key".equals(k)) {
                sb.append("<" + k + ">" + parameters.get(k) + "</" + k + ">\n");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 将XML格式的字符串转换为Map
     *
     * @param xmlData
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseMap(String xmlData) throws Exception {
        InputStream stream2 = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("UTF-8");
        Document document = (Document) saxReader.read(stream2);
        Element root = document.getRootElement();
        List<Element> childList = root.elements();
        Map<String, String> responseMap = new HashMap<String, String>();
        for (Element element : childList) {
            responseMap.put(element.getName(), element.getText());
        }
        return responseMap;
    }

    /**
     * post发送XML数据
     *
     * @param url 请求地址
     * @param map 待转换成XML的数据
     * @return
     * @throws Exception
     */
    public static SortedMap<String, String> sendXMLDataByPost(String url,
                                                              SortedMap<String, String> map) throws Exception {

        // https单向认证
        HttpURLConnection connection = SSLTrustManager.connect(url);
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setReadTimeout(30000);
        byte data[] = XMLUtils.parseXML(map).getBytes("utf-8");
        OutputStream out = connection.getOutputStream();
        out.write(data);
        StringBuffer receivedData = new StringBuffer();
        InputStreamReader inReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader aReader = new BufferedReader(inReader);
        String aLine;
        while ((aLine = aReader.readLine()) != null) {
            receivedData.append(aLine);
        }
        // Integer statusCode = connection.getResponseCode();
        String res = receivedData.toString();
        aReader.close();
        connection.disconnect();

        InputStream stream2 = new ByteArrayInputStream(res.getBytes("UTF-8"));
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("UTF-8");
        Document document = (Document) saxReader.read(stream2);
        Element root = document.getRootElement();
        List<Element> childList = root.elements();
        SortedMap<String, String> responseMap = new TreeMap<String, String>();
        for (Element element : childList) {
            responseMap.put(element.getName(), element.getText());
        }
        return responseMap;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) throws Exception {
        InputStream inStream = request.getInputStream();
        Map<String, String> parameterMap = new HashMap<String, String>();
        if (inStream != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] tempBytes = new byte[1024];
            int count;
            while ((count = inStream.read(tempBytes, 0, 1024)) != -1) {
                outStream.write(tempBytes, 0, count);
            }
            tempBytes = null;
            outStream.flush();
            String xmlString = new String(outStream.toByteArray(), "UTF-8");
            logger.info("渠道返回元数据：" + xmlString);
            parameterMap = XMLUtils.parseMap(xmlString);
        }
        return parameterMap;
    }
}
