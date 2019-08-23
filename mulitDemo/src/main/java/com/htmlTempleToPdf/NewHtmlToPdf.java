package com.htmlTempleToPdf;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

/**
 * @author lisong@mimidai.com
 * @date 2018/7/26 15:23
 */
public class NewHtmlToPdf {

    /**
     * 将HTML转成PD格式的文件。html文件的格式比较严格
     * @param htmlFile
     * @param pdfFile
     * @throws Exception
     */
    // <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
    public static void html2pdf(String htmlFile, String pdfFile) throws Exception {

        String data = getFileData(htmlFile);
        System.out.println("原内容："+data);
        int i = (int)(1+Math.random()*(20));

        System.out.println(HAIXIANG_GOOD_MAP.get(String.valueOf(i)));
        System.out.println(HAIXIANG_GOOD_MAP.get(String.valueOf(i)).get(0));
        System.out.println(HAIXIANG_GOOD_MAP.get(String.valueOf(i)).get(1));
        data = data.replaceAll("##username##","焦金龙");
        data = data.replaceAll("##phone##",new StringBuilder("18698587234").replace(3,7,"****").toString());
        data = data.replaceAll("##location##","河北邢台善各庄");
        data = data.replaceAll("##orderNo##","2018072709091800");
        data = data.replaceAll("##orderTime##","2018-07-27 09:09:18");
        data = data.replaceAll("##paymentTime##","2018-07-27 09:09:18");
        data = data.replaceAll("##totalMoney##",HAIXIANG_GOOD_MAP.get(String.valueOf(i)).get(1));
        data = data.replaceAll("#.jpg",HAIXIANG_GOOD_MAP.get(String.valueOf(i)).get(0));
        String newHtmlFile = "D:/project/IdeaProjects/mimidai/mimidai-pdf/index-new.html";
        creatNewHtml(newHtmlFile,data);
        // step 1
        String url = new File(newHtmlFile).toURI().toURL().toString();
        System.out.println(url);
        // step 2
        OutputStream os = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();


        renderer.setDocument(url);
//        renderer.setDocument(htmlFile);

        // step 3 解决中文支持
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if("linux".equals(getCurrentOperatingSystem())){
            fontResolver.addFont("/usr/share/fonts/chiness/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        }else{
            fontResolver.addFont("D:/project/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }
        //解决图片的相对路径问题
//        renderer.getSharedContext().setBaseURL("file:/D:/project/IdeaProjects/mimidai/mimidai-pdf/images");
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();

        System.out.println("create pdf done!!");

    }

    public static String getCurrentOperatingSystem(){
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("---------当前操作系统是-----------" + os);
        return os;
    }

    //读取文件中的内容到string
    public static String getFileData(String filePath) {
        StringBuffer document = new StringBuffer();
        try {
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null)
                document.append(line + " ");
            reader.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.toString();
    }

    public static Boolean creatNewHtml(String htmlFile,String data){
        try{
            System.out.println("新内容："+data);
            Long beginDate = new Date().getTime();
            File f = new File(htmlFile);
//            BufferedWriter o = new BufferedWriter(new FileWriter(f));
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
            BufferedWriter o = new BufferedWriter(write);
            o.write(data);
            o.close();
            System.out.println("共用时：" + ((new Date()).getTime() - beginDate) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 海象商品列表映射
     */
    public static Map<String,List<String>> HAIXIANG_GOOD_MAP = new HashMap<String, List<String>>();
    static {
        HAIXIANG_GOOD_MAP.put("1",new ArrayList<String>(){{add("1.png"); add("3499.00");}});
        HAIXIANG_GOOD_MAP.put("2",new ArrayList<String>(){{add("2.png"); add("4288.00");}});
        HAIXIANG_GOOD_MAP.put("3",new ArrayList<String>(){{add("3.png"); add("8799.00");}});
        HAIXIANG_GOOD_MAP.put("4",new ArrayList<String>(){{add("4.png"); add("11786.00");}});
        HAIXIANG_GOOD_MAP.put("5",new ArrayList<String>(){{add("5.png"); add("7299.00");}});
        HAIXIANG_GOOD_MAP.put("6",new ArrayList<String>(){{add("6.png"); add("5288.00");}});
        HAIXIANG_GOOD_MAP.put("7",new ArrayList<String>(){{add("7.png"); add("7599.00");}});
        HAIXIANG_GOOD_MAP.put("8",new ArrayList<String>(){{add("8.png"); add("4599.00");}});
        HAIXIANG_GOOD_MAP.put("9",new ArrayList<String>(){{add("9.png"); add("3999.00");}});
        HAIXIANG_GOOD_MAP.put("10",new ArrayList<String>(){{add("10.png"); add("4499.00");}});
        HAIXIANG_GOOD_MAP.put("11",new ArrayList<String>(){{add("11.png"); add("4699.00");}});
        HAIXIANG_GOOD_MAP.put("12",new ArrayList<String>(){{add("12.png"); add("5080.00");}});
        HAIXIANG_GOOD_MAP.put("13",new ArrayList<String>(){{add("13.png"); add("4499.00");}});
        HAIXIANG_GOOD_MAP.put("14",new ArrayList<String>(){{add("14.png"); add("7199.00");}});
        HAIXIANG_GOOD_MAP.put("15",new ArrayList<String>(){{add("15.png"); add("4999.00");}});
        HAIXIANG_GOOD_MAP.put("16",new ArrayList<String>(){{add("16.png"); add("5498.00");}});
        HAIXIANG_GOOD_MAP.put("17",new ArrayList<String>(){{add("17.png"); add("4999.00");}});
        HAIXIANG_GOOD_MAP.put("18",new ArrayList<String>(){{add("18.png"); add("3898.00");}});
        HAIXIANG_GOOD_MAP.put("19",new ArrayList<String>(){{add("19.png"); add("5799.00");}});
        HAIXIANG_GOOD_MAP.put("20",new ArrayList<String>(){{add("20.png"); add("5499.00");}});

    }

    public static void main(String[] args) {
        //        String htmlFile = "/home/lbj/sign.jsp";
        //        String pdfFile = "/home/lbj/sign.pdf";
//        String htmlFile = "http://www.zcool.com.cn/work/ZMjkwNjg5MjQ=.html";
        String htmlFile = "D:/project/IdeaProjects/mimidai/mimidai-pdf/index.html";
        String pdfFile = "d:/testoone3.pdf";


        try {
            html2pdf(htmlFile, pdfFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
