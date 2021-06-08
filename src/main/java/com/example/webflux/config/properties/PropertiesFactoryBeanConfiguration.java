package com.example.webflux.config.properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/5 - 22:30
 * @author: Mr_Bangb
 * 利用 PropertiesFactoryBean 管理 自定义properties文件的加载
 * 加载的properties文件的属性最终会放到一个Properties对象中
 * 要求各个properties文件中 不能有重复的key
 * 最终属性会保存在Properties对象中，在需要使用的地方利用@Resource(name="") 注入即可
 * 对象注入的时候 会调用PropertiesFactoryBean.getObject() 方法获取Properties对象
 */
@Configuration
public class PropertiesFactoryBeanConfiguration {

    private final static int LOAD_RESOURCE_NUMBER = 2;

    @Bean(name = "properties")
    public PropertiesFactoryBean getPropertiesFactoryBean() {
        // PropertiesFactoryBean 用于管理properties配置文件的工厂对象
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();

        // 待加载的自定义properties文件
        Resource dictionaryResource = new ClassPathResource("dictionary.properties");
        Resource versionResource = new ClassPathResource("version.properties");

        Resource[] resources = new Resource[LOAD_RESOURCE_NUMBER];
        resources[0] = dictionaryResource;
        resources[1] = versionResource;

        propertiesFactoryBean.setLocations(resources);

        return propertiesFactoryBean;
    }
}