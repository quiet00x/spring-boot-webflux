package com.example.webflux.controller;

import com.example.webflux.common.exception.LocalException;
import com.example.webflux.domain.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Properties;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/27 - 0:23
 * @author: Mr_Bangb
 */
@RestController
@RequestMapping("/dictionarys")
@Slf4j
public class DictionaryController {
    @Resource(name="dictProperties")
    private Properties dictProperties;

    @Resource(name="versionProperties")
    private Properties versionProperties;

    @Resource(name="properties")
    private Properties properties;

    /**
     * 背景：
     *  容器启动 加载 properties文件，构建数据字典
     * @param user
     */
    @RequestMapping(method = RequestMethod.GET)
    public void load(UserBean user) {

        log.info(dictProperties.get("userName").toString());

        log.info(versionProperties.get("projectVersion").toString());

        log.info(properties.getProperty("userId"));

        Optional.ofNullable(null).orElseThrow(LocalException::new);
    }
}