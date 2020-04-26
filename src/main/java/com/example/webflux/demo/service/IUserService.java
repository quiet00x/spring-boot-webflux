package com.example.webflux.demo.service;



import com.example.webflux.domain.UserBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 1:44
 * @author: Mr_Bangb
 */
public interface IUserService {

    public UserBean selectUser(Map<String, Object> map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    public List<UserBean> selectUserList(Map<String, Object> map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
