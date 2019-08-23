package com.htmlTempleToPdf;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.MalformedURLException;

/**
 * @author lisong@mimidai.com
 * @date 2018/7/26 15:23
 */
public class NewHtmlToPdfBak {

    /**
     * 将HTML转成PD格式的文件。html文件的格式比较严格
     * @param htmlFile
     * @param pdfFile
     * @throws Exception
     */
    // <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
    public static void html2pdf(String htmlFile, String pdfFile) throws Exception {
        // step 1
        String url = new File(htmlFile).toURI().toURL().toString();
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
        renderer.getSharedContext().setBaseURL("file:/D:/project/IdeaProjects/mimidai/mimidai-pdf/images");
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
