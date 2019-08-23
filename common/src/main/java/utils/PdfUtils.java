package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


/**
 * @author lisong@mimidai.com
 * @date 2018/7/26 16:23
 */
public class PdfUtils {
    private static final Logger logger = LoggerFactory.getLogger(PdfUtils.class);
    /**
     *copy文件
     */
    public static void rwFile(InputStream in){
        FileWriter fw = null;
        BufferedReader br = null;
        try {
            fw = new FileWriter("A:\\text.txt", true);
            br = new BufferedReader(new InputStreamReader(in,"utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                logger.info("文件内容.line={}" + line);
                fw.write(line);
                fw.flush();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 流转成PDF
     */
    public static void getPDF(InputStream in){
        try {
            FileOutputStream fo = new FileOutputStream("D:\\fx.pdf");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for(int i;(i=in.read())!=-1;){
                baos.write(i);
            }
            baos.flush();
            Document doc = new Document();
            PdfStream pdfStream=new PdfStream(baos.toByteArray());
            PdfWriter pw =PdfWriter.getInstance(doc, fo);
            pdfStream.toPdf(pw,fo);
            logger.info("doc内容.doc={}",doc.newPage());
            System.out.println("doc内容.doc=1{}"+doc.newPage());
            pw.flush();
            baos.close();
            pw.close();
            fo.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *流编码
     */
    public static byte[] encrypt(InputStream in) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*1024];
        int r;
        while ((r = in.read(buffer)) > 0) {
            bos.write(buffer, 0, r);
            bos.flush();
        }
        byte[] outBytes = bos.toByteArray();
        in.close();
        bos.close();
        return new Base64().encode(outBytes);
    }
    /**
     * 流解码
     *
     */
    public static byte[] decrypt(InputStream in) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int r;
        while ((r = in.read(buffer)) > 0) {
            bos.write(buffer, 0, r);
            bos.flush();
        }
        byte[] outBytes = bos.toByteArray();
        in.close();
        bos.close();
        return new Base64().encode(outBytes);
    }

    /**
     将一个字符串转化为输入流
    */
    public static InputStream getStringStream(String sInputString){
        if (sInputString != null && !sInputString.trim().equals("")){
            try{
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Description: 将base64编码内容转换为Pdf
     * @Author fuyuwei
     * Create Date: 2015年7月30日 上午9:40:23
     */
    public static void base64StringToPdf(String base64Content,String filePath){
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                bis.close();
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Description: 将pdf文件转换为Base64编码
     * @Author fuyuwei
     * Create Date: 2015年8月3日 下午9:52:30
     */
    public static String PDFToBase64(File file) {
        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void html2pdf(String htmlFile, String pdfFile) throws Exception {
        org.jsoup.nodes.Document doc = Jsoup.connect(htmlFile).get();
        /*Elements el = doc.select("#basePdf");
        String s = el.text();
        String s1 = el.html();
        System.out.println("create pdf done!!");
        System.out.println(el.first().text().length());
        System.out.println(s1);*/
        Element element=doc.getElementById("basePdf1");
        String menu=element.text(); // 返回元素的文本
        System.out.println(doc.getElementById("renderPdf").text());
        System.out.println("导航："+element.text().length());
        System.out.println("导航1："+element.text());
//        base64StringToPdf(menu,"D:\\fxqa.pdf");


    }

    public static void html2pdf1(String htmlFile, String pdfFile) throws Exception {

        Connection conn = Jsoup.connect(htmlFile);
        conn.method(Connection.Method.GET);
        conn.followRedirects(false);
        Connection.Response response = conn.execute();
        System.out.println(response.cookies());
//        base64StringToPdf(menu,"D:\\fxqa.pdf");


    }
    public static void main(String[] args) {


        try {
            html2pdf("http://192.168.1.47:54023/?username=%E6%9D%8E%E5%98%89%E8%AF%9A&phone=18810329065&loca=%E5%8C%97%E4%BA%AC%E5%B8%82%E5%9B%9B%E6%83%A0%E5%A4%A7%E5%8E%A63%E5%B1%823001E","");
        } catch (Exception e) {
            e.printStackTrace();
        }


//        base64StringToPdf(a,"D:\\fxq.pdf");
    }
}
