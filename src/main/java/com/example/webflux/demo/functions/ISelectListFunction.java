package com.example.webflux.demo.functions;


import com.example.webflux.domain.UserBean;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 1:00
 * @author: Mr_Bangb
 */
@FunctionalInterface
public interface ISelectListFunction {
    List<UserBean> selectUserList(List<Long> ids);
}
