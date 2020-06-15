package com.example.webflux.common.exception;

import com.example.webflux.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

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
        StringBuilder messageBuilder = new StringBuilder();
        for (Object arg : args) {
            messageBuilder.append(arg);
        }
        this.errorMessage = messageBuilder.toString();
    }

    public LocalException(ResultEnum resultEnum) {
        super(resultEnum.getCode());
        this.errorCode = resultEnum.getCode();
        this.errorMessage = resultEnum.getMessage();
    }
}