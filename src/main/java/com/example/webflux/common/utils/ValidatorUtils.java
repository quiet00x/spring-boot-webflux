package com.example.webflux.common.utils;


import com.example.webflux.common.exception.LocalException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/5 - 0:14
 * @author: Mr_Bangb
 */
public class ValidatorUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws LocalException  校验不通过，LocalException
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws LocalException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            String errMessages = constraintViolations.stream()
                    .map(item -> item.getMessage()).collect(Collectors.joining(","));
            throw new LocalException(errMessages);
        }
    }
}