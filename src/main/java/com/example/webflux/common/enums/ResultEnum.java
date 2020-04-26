package com.example.webflux.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:58
 * @author: Mr_Bangb
 * 枚举常量类 不要提供setter方法
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    /**
     * 表示请求成功
     */
    SUCCESS(1000, "请求成功"),
    /**
     * 表示请求失败，业务异常
     */
    FAILE(0000,"请求失败"),
    /**
     * 表示请求失败，请求参数异常
     */
    PARAMETER_ERROR(1001, "请求参数有误!"),
    /**
     * 表示系统异常，出现未知错误
     */
    UNKNOWN_ERROR(9999, "未知的错误!");

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;

    /**
     * 根据key 获取 枚举常量的value
     * @param inputKey
     * @return
     */
    public static String getValueByKey(Integer inputKey) {
        ResultEnum[] enums = ResultEnum.values();
        for (ResultEnum  curEnum: enums) {
            Integer curCode = curEnum.getCode();
            if (curCode == inputKey) {
                return curEnum.getMessage();
            }
        }
        return null;
    }

    /**
     * 将枚举转换为Map
     * @return Map<Integer,String> map
     */
    public static Map<Integer,String> converToMap(){
        Map<Integer,String> enumMap = new HashMap<>();
        ResultEnum[] enums = ResultEnum.values();
        for (ResultEnum  curEnum: enums) {
            enumMap.put(curEnum.getCode(),curEnum.getMessage());
        }
        return enumMap;
    }
}