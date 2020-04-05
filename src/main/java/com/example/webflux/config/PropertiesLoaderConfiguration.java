package com.example.webflux.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/6 - 0:48
 * @author: Mr_Bangb
 * 利用Properties对象，ClassLoader类加载器，加载resources目录下的自定义properties文件
 * 最终属性会保存在Properties对象中，在需要使用的地方利用@Resource(name="") 注入即可
 * 相对较灵活，一个配置类可以加载多个配置文件
 * 建议使用！
 */
@Configuration
@Slf4j
public class PropertiesLoaderConfiguration {


    private Properties loadPropertiesFile(String name) throws IOException {

        Properties properties = new Properties();

        InputStream in = PropertiesLoaderConfiguration.class.getClassLoader().getResourceAsStream(name + ".properties");

        log.info("*********************** 加载{}.properties ***********************", name);

        properties.load(in);

        log.info("*********************** 加载{}.properties完毕 ***********************", name);

        return properties;

    }

    @Bean(name = "dictProperties")
    public Properties getDictPropertiesInstance() throws IOException {
        return loadPropertiesFile("dictionary");
    }

    @Bean(name = "versionProperties")
    public Properties getVersionPropertiesInstance() throws IOException {
        return loadPropertiesFile("version");
    }

}