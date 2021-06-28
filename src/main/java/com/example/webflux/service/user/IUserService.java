package com.example.webflux.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webflux.domain.CategoryBean;
import com.example.webflux.domain.ExtendBean;
import com.example.webflux.domain.UserBean;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface IUserService extends IService<UserBean> {
    CategoryBean getOrgnations(String id);

    ExtendBean getExtends(String id);
}