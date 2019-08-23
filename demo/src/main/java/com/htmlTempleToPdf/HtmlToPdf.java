package com.htmlTempleToPdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Entities;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.URL;

/**
 * @author lisong@mimidai.com
 * @date 2018/7/25 15:00
 */
public class HtmlToPdf {

    public static void main(String[] args) {
//        String url = "https://www.cnblogs.com/licomeback/articles/3056492.html";
        String url = "http://www.zcool.com.cn/work/ZMjkwNjg5MjQ=.html";
//        String url = "http://192.168.1.100:8080/?username=%E6%9D%8E%E9%B9%8F%E8%BE%89&phone=18810329065&loca=%E5%8C%97%E4%BA%AC%E5%B8%82%E5%9B%9B%E6%83%A0%E5%A4%A7%E5%8E%A63%E5%B1%823001E";
        String pdfUrl = "D:/project/pdftest.pdf";
        try {
            convertHtmlToPdf(url,pdfUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static boolean convertHtmlToPdf(String inputFile, String outputFile)
            throws Exception {

        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        // 解决中文支持问题
        // ITextFontResolver fontResolver = renderer.getFontResolver();
        String uri = new File(inputFile).toURI().toURL().toString();
        renderer.getFontResolver().addFont("D:/project/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        org.jsoup.nodes.Document doc = Jsoup.parse(new URL(inputFile),1000);
        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml);
        renderer.setDocumentFromString(doc.html(),inputFile);
        //解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:/D:/");
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
        os.flush();
        os.close();
        return true;
    }

    public static void xmlToPdf(){
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
            // step 3
            document.open();
            // step 4
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream("index.html"));
            //step 5
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "PDF Created!" );
    }
}
