package com.example.webflux.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 1:25
 * @author: Mr_Bangb
 */
@Data
@AllArgsConstructor
@Slf4j
public class LocalException extends RuntimeException {
    /**
     * 异常码
     */
    private String errorCode;
    /**
     * 异常信息
     */
    private String errorMessage;

    /**
     * 无参构造
     */
    public LocalException() {
        super();
    }

    public LocalException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public LocalException(String errorCode, Object ... args) {
        super(errorCode);
        this.errorCode = errorCode;
        StringBuilder builer = new StringBuilder();
        for (Object arg : args) {
            builer.append(arg);
        }
        this.errorMessage = builer.toString();
    }

}