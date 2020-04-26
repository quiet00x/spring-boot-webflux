package com.example.webflux.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webflux.domain.UserBean;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:46
 * @author: Mr_Bangb
 */
public interface UserMapper extends BaseMapper<UserBean> {

    /**
     * 查询用户列表
     * @param userBean
     * @return
     */
    List<UserBean> mySelectUsers(UserBean userBean);

    UserBean selectSalesUsers(UserBean userBean);

    UserBean selectAdminUsers(UserBean userBean);

    UserBean selectPerUsers(UserBean userBean);

    UserBean selectPurUsers(UserBean userBean);

    UserBean selectUserByTransCode(UserBean userBean);
}
