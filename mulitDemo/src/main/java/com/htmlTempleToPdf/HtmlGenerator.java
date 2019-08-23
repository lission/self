package com.htmlTempleToPdf;

/**
 * @author lisong@mimidai.com
 * @date 2018/7/26 16:14
 */

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.Map;

public class HtmlGenerator {
    /**
     * @param template
     * @param variables
     * @return
     * @throws Exception
     */
    public static String generate(String template, Map params) throws Exception{
        Configuration config = FreemarkerConfiguration.getConfiguation();
        config.setDefaultEncoding("UTF-8");
        Template tp = config.getTemplate(template);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        tp.setEncoding("UTF-8");
        tp.process(params, writer);
        String htmlStr = stringWriter.toString();
        writer.flush();
        writer.close();
        return htmlStr;
    }

}