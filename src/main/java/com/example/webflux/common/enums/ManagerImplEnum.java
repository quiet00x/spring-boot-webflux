package com.example.webflux.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/4 - 0:19
 * @author: Mr_Bangb
 */
@AllArgsConstructor
@Getter
public enum ManagerImplEnum {
    NAME_MANAGER_SERVICE("updateName","nameManagerService"),
    PROPERTY_MANAGER_SERVICE("updateProperty","propertyManagerService");

    private String operType;

    private String serviceImplName;

    /**
     * 根据key 获取 枚举常量的value
     * @param inputKey
     * @return
     */
    public static <T> Optional<String> getValueByKey(String inputKey) {
        ManagerImplEnum[] enums = ManagerImplEnum.values();
        for (ManagerImplEnum curEnum: enums) {
            String curCode = curEnum.getOperType();
            if (StringUtils.equals(curCode,inputKey)) {
                return Optional.ofNullable(curEnum.getServiceImplName());
            }
        }
        return Optional.empty();
    }

    /**
     * 将枚举转换为Map
     * @return Map<Integer,String> map
     */
    public static Map<String,String> converToMap(){
        Map<String,String> enumMap = new HashMap<>();
        ManagerImplEnum[] enums = ManagerImplEnum.values();
        for (ManagerImplEnum curEnum: enums) {
            enumMap.put(curEnum.getOperType(),curEnum.getServiceImplName());
        }
        return enumMap;
    }
}