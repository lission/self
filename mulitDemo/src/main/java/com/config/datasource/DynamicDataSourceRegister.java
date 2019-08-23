package com.config.datasource;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * springboot集成mybatis的基本入口
 * @Description 注册动态数据源
 * 初始化数据源和提供了执行动态切换数据源的工具类
 * EnvironmentAware（获取配置文件配置的属性值）
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class DynamicDataSourceRegister implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    /**
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
    private Environment environment;

    @Autowired
    private MybatisProperties properties;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource masterDataSource() throws Exception {
        logger.info("开始初始化数据库(master)配置...");
        return buildDataSource("spring.datasource.master");
    }

    @Bean
    public DataSource slaveDataSource() throws Exception {
        logger.info("开始初始化数据库(slave)配置...");

        return buildDataSource("spring.datasource.slave");
    }

    /**
    *@Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
    *@Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
    */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave", slaveDataSource);

        DynamicDataSourceContextHolder.dataSourceIds.add("master");
        DynamicDataSourceContextHolder.dataSourceIds.add("slave");
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为myTestDbDataSource
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        factory.setMapperLocations(this.properties.resolveMapperLocations());
        return factory.getObject();
    }


    /**
     * 构造数据源
     *
     * @param dataSourcePrefix 数据源前缀
     */

    private DataSource buildDataSource(String dataSourcePrefix) throws Exception {
        /**
         * 参数绑定工具 springboot2.0新推出
         */
        Binder binder = Binder.get(environment);
        Map defauleDataSourceProperties = binder.bind(dataSourcePrefix, Map.class).get();

        Properties props = new Properties();
        props.put("driverClassName", defauleDataSourceProperties.get("driver-class-name"));
        props.put("url", defauleDataSourceProperties.get("url"));
        /*加密的*/
        /*props.put("username", ConfigTools.decrypt(defauleDataSourceProperties.get("username").toString()));
        props.put("password", ConfigTools.decrypt(defauleDataSourceProperties.get("password").toString()));*/
        /*明文*/
        props.put("username", defauleDataSourceProperties.get("username"));
        props.put("password", defauleDataSourceProperties.get("password"));
        props.put("maxActive", defauleDataSourceProperties.get("maxActive").toString());
        //		props.put("maxIdle", defauleDataSourceProperties.get("maxIdle"));
        props.put("minIdle", defauleDataSourceProperties.get("minIdle").toString());
        props.put("validation-query", defauleDataSourceProperties.get("validation-query"));

        DruidDataSource dataSource =  (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        dataSource.setFilters("stat,wall");
        dataSource.setUseGlobalDataSourceStat(true);
        logger.info("初始化数据库{}配置完成,url={},maxActive={},maxIdle={},minIdle={}",dataSourcePrefix, defauleDataSourceProperties.get
                ("url"), dataSource.getMaxActive(), dataSource.getMaxIdle(), dataSource.getMinIdle());
        return dataSource;
    }
}
