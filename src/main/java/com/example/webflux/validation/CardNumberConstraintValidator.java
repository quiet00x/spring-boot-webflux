package com.example.webflux.validation;

import com.example.webflux.validation.constraints.ValidCardNumber;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:00
 * @author: Mr_Bangb
 * 自定义约束校验器
 */
public class CardNumberConstraintValidator implements ConstraintValidator<ValidCardNumber,String> {

    private final String SEPARATOR_CHARS = "-";

    private final String VALID_PREFIX = "BANGB";

    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {

    }

    /**
     * 校验规则：
     * 1. 前缀必须是BANGB-
     * 2. 后缀必须是数字
     * @param cardNumber
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        /*
         慎用JDK原生的 String.spilt
         1. 因为对NPE 保护不够
         2. 因为底层使用的正则，性能不好
         */
        String[] parts = StringUtils.split(cardNumber,SEPARATOR_CHARS);

        if (ArrayUtils.getLength(parts) != 2) {
            return false;
        }

        String prefix = parts[0];
        String suffer = parts[parts.length];

        Boolean isValidPrefix = StringUtils.equals(VALID_PREFIX,prefix);

        Boolean isValidSuffer = StringUtils.isNumeric(suffer);

        return isValidPrefix && isValidSuffer;
    }
}