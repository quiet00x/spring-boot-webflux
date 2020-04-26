package com.example.webflux.common.enums;

import com.example.webflux.common.constant.TransCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/21 - 23:36
 * @author: Mr_Bangb
 */
@AllArgsConstructor
@Getter
public enum TableEnum {

    SALES_USER_TABLE(TransCodeConstant.SELECT_SALES_DEPARTMENT_USER,"SALES_USER"),
    ADMIN_USER_TABLE(TransCodeConstant.SELECT_ADMIN_DEPARTMENT_USER,"ADMIN_USER"),
    PER_USER_TABLE(TransCodeConstant.SELECT_PER_DEPARTMENT_USER,"PER_USER"),
    PUR_USER_TABLE(TransCodeConstant.SELECT_PUR_DEPARTMENT_USER,"PUR_USER");

    private String transCode;

    private String tableName;

    /**
     * 根据key 获取 枚举常量的value
     * @param inputKey
     * @return
     */
    public static String getValueByKey(String inputKey) {
        TableEnum[] enums = TableEnum.values();
        for (TableEnum  curEnum: enums) {
            String curCode = curEnum.getTransCode();
            if (StringUtils.equals(curCode,inputKey)) {
                return curEnum.getTableName();
            }
        }
        return null;
    }

    /**
     * 将枚举转换为Map
     * @return Map<Integer,String> map
     */
    public static Map<String,String> converToMap(){
        Map<String,String> enumMap = new HashMap<>();
        TableEnum[] enums = TableEnum.values();
        for (TableEnum  curEnum: enums) {
            enumMap.put(curEnum.getTransCode(),curEnum.getTableName());
        }
        return enumMap;
    }

}
