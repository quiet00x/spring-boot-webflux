package com.example.webflux.service.pbi.impl;

import com.example.webflux.common.enums.ManagerImplEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.service.pbi.IManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/4 - 23:16
 * @author: Mr_Bangb
 */
@Service
@Slf4j
public class ManagerBaseService {
    private Map<String, String> iManagerServiceImplNameMap = ManagerImplEnum.converToMap();

    @Resource
    private ApplicationContext applicationContext;

    protected IManageService getManageServiceImpl(String operType) {
        Map<String, IManageService> iManagerServiceBeansMap =
                applicationContext.getBeansOfType(IManageService.class);
        String className = getClassName(operType);
        IManageService iManagerService = iManagerServiceBeansMap.get(className);
        return iManagerService;
    }
    
    private String getClassName(String operType) {
        String className = iManagerServiceImplNameMap.get(operType);
        Optional.ofNullable(className)
                .orElseThrow(() -> new LocalException("operType不正确"));
        return className;
    }
}