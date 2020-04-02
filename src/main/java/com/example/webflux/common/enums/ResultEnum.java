package com.example.webflux.common.enums;

import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:58
 * @author: Mr_Bangb
 */
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

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}