package com.example.webflux.demo.service.impl;

import com.example.webflux.demo.constants.Constants;
import com.example.webflux.demo.dao.ISelectDao;
import com.example.webflux.demo.dao.SelectDaoImpl;
import com.example.webflux.demo.functions.ISelectListFunction;
import com.example.webflux.demo.functions.ISelectObjFunction;
import com.example.webflux.domain.UserBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 23:01
 * @author: Mr_Bangb
 * 工具类
 */
public class SelectUtils<T> {

    private ISelectDao selectDao = new SelectDaoImpl();

    T builderObjMap(ISelectObjFunction selectObj){

        UserBean userBean = selectObj.selectUser(null);
        return (T) userBean;
    }
    public T orElse(T other) {
        return other;
    }

    List<T> builderListMap(ISelectListFunction selectList){
        Map<String, ISelectListFunction> map = new HashMap<>();
        map.put(Constants.KEY_01, ids -> selectDao.selectUserList01(ids));
        map.put(Constants.KEY_02, ids -> selectDao.selectUserList02(ids));
        map.put(Constants.KEY_03, ids -> selectDao.selectUserList03(ids));
        return null;
    }
}
