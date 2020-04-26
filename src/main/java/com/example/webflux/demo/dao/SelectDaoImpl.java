package com.example.webflux.demo.dao;



import com.example.webflux.domain.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 1:03
 * @author: Mr_Bangb
 */
public class SelectDaoImpl implements ISelectDao{

    @Override
    public List<UserBean> selectUserList01(List<Long> ids) {
        UserBean userBean1 = UserBean.builder().id(1L).userName("quiet").age("18").build();
        UserBean userBean2 = UserBean.builder().id(2L).userName("quiet00x").age("18").build();
        List<UserBean> list = new ArrayList<>();
        list.add(userBean1);
        list.add(userBean2);
        return list;
    }

    @Override
    public List<UserBean> selectUserList02(List<Long> ids) {
        UserBean userBean1 = UserBean.builder().id(3L).userName("quiet00j").age("20").build();
        UserBean userBean2 = UserBean.builder().id(4L).userName("slag").age("20").build();
        List<UserBean> list = new ArrayList<>();
        list.add(userBean1);
        list.add(userBean2);
        return list;
    }

    @Override
    public List<UserBean> selectUserList03(List<Long> ids) {
        UserBean userBean1 = UserBean.builder().id(5L).userName("hahhy").age("23").build();
        UserBean userBean2 = UserBean.builder().id(6L).userName("prayer").age("23").build();
        List<UserBean> list = new ArrayList<>();
        list.add(userBean1);
        list.add(userBean2);
        return list;
    }

    @Override
    public UserBean selectUserObject01(Long id) {
        UserBean userBean = UserBean.builder().id(id).userName("yoyo").age("28").build();
        return userBean;
    }

    @Override
    public UserBean selectUserObject02(Long id) {
        UserBean userBean = UserBean.builder().id(id).userName("bangb").age("28").build();
        return userBean;
    }

    @Override
    public UserBean selectUserObject03(Long id) {
        UserBean userBean = UserBean.builder().id(id).userName("sanni").age("29").build();
        return userBean;
    }
}