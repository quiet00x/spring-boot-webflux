package com.example.webflux.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webflux.domain.UserBean;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface UserService extends IService<UserBean> {
    /**
     * 各类user 信息查询
     * @param userBean
     * @return
     */
    UserBean selectUsersByCondition(UserBean userBean);
}