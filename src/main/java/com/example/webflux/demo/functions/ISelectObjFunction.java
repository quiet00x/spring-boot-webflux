package com.example.webflux.demo.functions;


import com.example.webflux.domain.UserBean;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 1:00
 * @author: Mr_Bangb
 */
@FunctionalInterface
public interface ISelectObjFunction {
    UserBean selectUser(Long id);
}
