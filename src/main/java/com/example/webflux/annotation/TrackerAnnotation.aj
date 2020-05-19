package com.example.webflux.annotation;

import java.lang.annotation.*;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 23:15
 * @author: Mr_Bangb
 */
// 表示这个注解可以用用在类/接口上，还可以用在方法上
@Target({ElementType.METHOD,ElementType.TYPE})
// 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息，而不像基本注解
@Retention(RetentionPolicy.RUNTIME)
// 表示这个注解可以被子类继承
@Inherited
// 表示当执行javadoc的时候，本注解会生成相关文档
@Documented
public @interface TrackerAnnotation {
    String value() default ""  ;
}
