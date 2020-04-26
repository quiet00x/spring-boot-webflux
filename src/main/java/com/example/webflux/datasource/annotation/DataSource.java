package com.example.webflux.datasource.annotation;

import java.lang.annotation.*;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/13 - 23:19
 * @author: Mr_Bangb
 * 自定义注解 多数据源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}