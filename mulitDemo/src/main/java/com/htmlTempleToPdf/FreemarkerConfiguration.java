package com.htmlTempleToPdf;
import freemarker.template.Configuration;

/**
 * @author lisong@mimidai.com
 * @date 2018/7/26 16:15
 */
public class FreemarkerConfiguration {

    private static Configuration config = null;

    /**
     * Static initialization.
     *
     * Initialize the configuration of Freemarker.
     */
    static{
        config = new Configuration();
        config.setClassForTemplateLoading(FreemarkerConfiguration.class, "/../../template/");
        config.setTemplateUpdateDelay(0);
    }

    public static Configuration getConfiguation(){
        return config;
    }

}
