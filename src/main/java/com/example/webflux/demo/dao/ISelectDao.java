package com.example.webflux.demo.dao;

import com.example.webflux.domain.UserBean;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 22:55
 * @author: Mr_Bangb
 */
public interface ISelectDao {
    public UserBean selectUserObject01(Long id);
    public UserBean selectUserObject02(Long id);
    public UserBean selectUserObject03(Long id);
    public List<UserBean> selectUserList01(List<Long> ids);
    public List<UserBean> selectUserList02(List<Long> ids);
    public List<UserBean> selectUserList03(List<Long> ids);

}
