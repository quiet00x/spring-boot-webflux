package com.example.webflux.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webflux.domain.UserBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface UserService extends IService<UserBean> {

    /**
     * user列表 查询
     * @param userBean
     * @return
     */
    List<UserBean> mySelectUsers(UserBean userBean);

    /**
     * 各类user 信息查询
     * @param userBean
     * @return
     */
    UserBean selectUsersByCondition(UserBean userBean);
}