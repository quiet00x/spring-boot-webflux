package com.example.webflux.common.enums;

import com.example.webflux.common.constant.ReqConstant;
import com.example.webflux.common.constant.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    SUCCESS(ResponseConstant.RSP_NORMAL_CODE, ResponseConstant.RSP_NORMAL_MSG),
    /**
     * 表示请求失败，请求参数异常，不允许为空
     */
    FAILED_PARAMETER_NOT_NULL_ERROR(ResponseConstant.ILLEGAL_NULL_PARAM_EXCEPTION_CODE, ResponseConstant.ILLEGAL_NULL_PARAM_EXCEPTION_MSG),
    /**
     * 表示请求失败，请求参数异常，参数值不正确
     */
    FAILED_PARAMETER_VALUE_ERROR(ResponseConstant.ILLEGAL_ARGUMENT_EXCEPTION_CODE, ResponseConstant.ILLEGAL_ARGUMENT_EXCEPTION_MSG),
    /**
     *  表示请求失败，系统异常，出现未知错误
     */
    FAILED_UNKNOWN_ERROR(ResponseConstant.UNKNOW_SYSTEM_EXCEPTION_CODE, ResponseConstant.UNKNOW_SYSTEM_EXCEPTION_MSG);

    /**
     * 响应码
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;

    /**
     * 根据key 获取 枚举常量的value
     * @param inputKey
     * @return
     */
    public static Optional<String> getValueByKey(String inputKey) {
        ResultEnum[] enums = ResultEnum.values();
        for (ResultEnum  curEnum: enums) {
            String curCode = curEnum.getCode();
            if (StringUtils.equals(curCode,inputKey)) {
                return Optional.ofNullable(curEnum.getMessage());
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
        ResultEnum[] enums = ResultEnum.values();
        for (ResultEnum  curEnum: enums) {
            enumMap.put(curEnum.getCode(),curEnum.getMessage());
        }
        return enumMap;
    }
}