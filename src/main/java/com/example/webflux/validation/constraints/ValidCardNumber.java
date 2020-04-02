package com.example.webflux.validation.constraints;

import com.example.webflux.validation.CardNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 22:04
 * @author: Mr_Bangb
 * 自定义参数校验注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CardNumberConstraintValidator.class})
public @interface ValidCardNumber {

    String message() default "{com.example.webflux.validation.constraints.ValidCardNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
