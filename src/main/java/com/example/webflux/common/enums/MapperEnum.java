package com.example.webflux.common.enums;

import com.example.webflux.common.constant.TransCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/21 - 23:36
 * @author: Mr_Bangb
 */
@AllArgsConstructor
@Getter
public enum MapperEnum {

    SELECT_SALES_USER_API(TransCodeConstant.SELECT_SALES_DEPARTMENT_USER,"selectSalesUsers"),
    SELECT_ADMIN_USER_TABLE_API(TransCodeConstant.SELECT_ADMIN_DEPARTMENT_USER,"selectAdminUsers"),
    SELECT_PER_USER_TABLE_API(TransCodeConstant.SELECT_PER_DEPARTMENT_USER,"selectPerUsers"),
    SELECT_PUR_USER_TABLE_API(TransCodeConstant.SELECT_PUR_DEPARTMENT_USER,"selectPurUsers");

    private String transCode;

    private String tableName;

    /**
     * 根据key 获取 枚举常量的value
     * @param inputKey
     * @return
     */
    public static <T> Optional<String> getValueByKey(String inputKey) {
        MapperEnum[] enums = MapperEnum.values();
        for (MapperEnum curEnum: enums) {
            String curCode = curEnum.getTransCode();
            if (StringUtils.equals(curCode,inputKey)) {
                return Optional.ofNullable(curEnum.getTableName());
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
        MapperEnum[] enums = MapperEnum.values();
        for (MapperEnum curEnum: enums) {
            enumMap.put(curEnum.getTransCode(),curEnum.getTableName());
        }
        return enumMap;
    }

}
