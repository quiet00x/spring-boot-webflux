package com.example.webflux.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/5 - 3:47
 * @author: Mr_Bangb
 * 数据字典，存放常用的共有属性
 * 1.利用@PropertySource("classpath:dictionary.properties") 加载自定义properties文件，
 * 2.并将属性注入到DictionaryBean中
 */
@Configuration
@PropertySource("classpath:dictionary.properties")
public class DictionaryPropertiesConfiguration {

    @Bean(name = "dictionaryBean")
    public DictionaryBean getDictionaryBean() {
        return new DictionaryBean();
    }

    @Data
    public class DictionaryBean {
        @Value("${userId}")
        private String userId;
        @Value("${userName}")
        private String userName;
        @Value("${userEmail}")
        private String userEmail;
        @Value("${shareDiskPath}")
        private String shareDiskPath;
    }

}