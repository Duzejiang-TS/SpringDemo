package com.example.demomybatis.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * @author Devin13 on 2018/8/10.
 * @version 1.0
 */
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    //自定义MyBatis的配置，驼峰命名法
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        //自定义MyBatis的配置，驼峰命名法
        return new ConfigurationCustomizer() {

            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
