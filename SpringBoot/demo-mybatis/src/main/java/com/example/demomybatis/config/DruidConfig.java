package com.example.demomybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Devin13 on 2018/8/9.
 * @version 1.0
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    //配置Druid的监控
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        initParams.put("loginUsername","Devin");
        initParams.put("loginPassword","123");
        initParams.put("allow","");//默认就是允许说有访问
        //initParams.put("deny","");//阻止
        bean.setInitParameters(initParams);
        return bean;
    }

    //配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String ,String> inirParams = new HashMap<>();
        inirParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(inirParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}