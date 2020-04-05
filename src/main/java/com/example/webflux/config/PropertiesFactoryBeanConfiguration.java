package com.example.webflux.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/5 - 22:30
 * @author: Mr_Bangb
 */
@Configuration
public class PropertiesFactoryBeanConfiguration {

    @Bean(name = "properties")
    public PropertiesFactoryBean getPropertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        Resource resource =
//        propertiesFactoryBean.setLocations();
        return propertiesFactoryBean;
    }


}