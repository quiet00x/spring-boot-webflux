package com.example.webflux.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webflux.domain.UserBean;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:46
 * @author: Mr_Bangb
 */
public interface UserMapper extends BaseMapper<UserBean> {
    UserBean selectSalesUsers(UserBean userBean);

    UserBean selectAdminUsers(UserBean userBean);

    UserBean selectPerUsers(UserBean userBean);

    UserBean selectPurUsers(UserBean userBean);

    UserBean selectUserByTransCode(UserBean userBean);
}
