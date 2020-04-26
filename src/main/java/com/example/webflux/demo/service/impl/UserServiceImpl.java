package com.example.webflux.demo.service.impl;



import com.baomidou.mybatisplus.extension.api.R;
import com.example.webflux.common.constant.WsErrConstant;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.demo.constants.Constants;
import com.example.webflux.demo.dao.ISelectDao;
import com.example.webflux.demo.dao.SelectDaoImpl;
import com.example.webflux.demo.functions.ISelectListFunction;
import com.example.webflux.demo.functions.ISelectObjFunction;
import com.example.webflux.demo.service.IUserService;
import com.example.webflux.domain.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 1:42
 * @author: Mr_Bangb
 */
@Slf4j
public class UserServiceImpl implements IUserService {

    private ISelectDao selectDao = new SelectDaoImpl();

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 模拟调用 service.selectUser
        // 获取请求参数
        Map<String, Object> paramObj = generalSelectObj();
        new UserServiceImpl().selectUser(paramObj);

        System.out.println("---------------------< ***************************** >---------------------");

        // 模拟调用 service.selectUserList
        // 获取请求参数
        Map<String, Object>  paramList = generalSelectList();
        new UserServiceImpl().selectUserList(paramList);


    }


    @Override
    public UserBean selectUser(Map<String, Object> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取 key 判断调用哪个dao方法
        String type = (String) params.get("key");
        Long id = (Long) params.get("id");

//        Map<String, Object> buildbehaviour = buildbehaviour();
//        Map<String, ISelectObjFunction> map =
//                (Map<String, ISelectObjFunction>) buildbehaviour.get("getObj");
//        UserBean user = map.get(type).selectUser(id);

        String methodName = "selectUserObject01";

        Class clz = selectDao.getClass();
        Method method = clz.getDeclaredMethod(methodName,Long.class);
        method.setAccessible(true);
        Object[] args = {id};
        UserBean user = (UserBean) method.invoke(selectDao,args);

//        UserBean user = null;
//        if (StringUtils.equals(type,Constants.KEY_01)) {
//            user = selectDao.selectUserObject01(id);
//        } else if (StringUtils.equals(type,Constants.KEY_02)) {
//            user = selectDao.selectUserObject02(id);
//        } else if (StringUtils.equals(type,Constants.KEY_03)) {
//            user =selectDao.selectUserObject03(id);
//        } else {
//            throw new LocalException(WsErrConstant.REQ_PARAMS_ERROR_CODE);
//        }

        System.out.println(user);

        return user;
    }

    private Map<String, ISelectObjFunction> buildSelectObjMap() {
        Map<String, ISelectObjFunction> map = new HashMap<>();
        map.put(Constants.KEY_01, id -> selectDao.selectUserObject01(id));
        map.put(Constants.KEY_02, id -> selectDao.selectUserObject02(id));
        map.put(Constants.KEY_03, id -> selectDao.selectUserObject03(id));
        return map;
    }


    @Override
    public List<UserBean> selectUserList(Map<String, Object> params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取 key 判断调用哪个dao方法
        String type = (String) params.get("key");
        List<Long> ids = (List<Long>) params.get("ids");
        List<UserBean> userList = null;

        long start = System.currentTimeMillis();

        for(int i = 0; i<=1000000;i++){
            String methodName = "selectUserList01";
            Class clz = selectDao.getClass();
            Method method = clz.getDeclaredMethod(methodName,List.class);
            method.setAccessible(true);
            Object[] args = {ids};
            userList = (List<UserBean>) method.invoke(selectDao,args);
        }

        long end = System.currentTimeMillis();

        System.out.println("reflect: " + (end-start));

//        List<UserBean> userList = null;
        start = System.currentTimeMillis();
        for(int i = 0; i<=1000000;i++) {
            if (StringUtils.equals(type, Constants.KEY_04)) {
                userList = selectDao.selectUserList01(ids);
            } else if (StringUtils.equals(type, Constants.KEY_05)) {
                userList = selectDao.selectUserList02(ids);
            } else if (StringUtils.equals(type, Constants.KEY_06)) {
                userList = selectDao.selectUserList03(ids);
            } else {
                throw new LocalException(WsErrConstant.REQ_PARAMS_ERROR_CODE);
            }
        }
        end = System.currentTimeMillis();

        System.out.println("if else: " + (end-start));

        start = System.currentTimeMillis();
        for(int i = 0; i<=1000000;i++) {
            Map<String, Object> buildbehaviour = buildbehaviour();
            Map<String, ISelectListFunction> map =
                    (Map<String, ISelectListFunction>) buildbehaviour.get("getList");
            userList = map.get(type).selectUserList(ids);
        }

        end = System.currentTimeMillis();
        System.out.println("lambda: " + (end-start));

        if (CollectionUtils.isEmpty(userList)){
            userList = Collections.emptyList();
        }

        userList.forEach(user -> System.out.println(user));

        return userList;
    }

    private Map<String, ISelectListFunction> buildSelectListMap() {
        Map<String, ISelectListFunction> map = new HashMap<>();
        map.put(Constants.KEY_01, ids -> selectDao.selectUserList01(ids));
        map.put(Constants.KEY_02, ids -> selectDao.selectUserList02(ids));
        map.put(Constants.KEY_03, ids -> selectDao.selectUserList03(ids));
        return map;
    }

    private Map<String,Object> buildbehaviour() {
        Map<String, Object> behaviourMap = new HashMap<>();
        Map<String, ISelectListFunction> map = new HashMap<>();
        map.put(Constants.KEY_04, ids -> selectDao.selectUserList01(ids));
        map.put(Constants.KEY_05, ids -> selectDao.selectUserList02(ids));
        map.put(Constants.KEY_06, ids -> selectDao.selectUserList03(ids));

        Map<String, ISelectObjFunction> map2 = new HashMap<>();
        map2.put(Constants.KEY_01, id -> selectDao.selectUserObject01(id));
        map2.put(Constants.KEY_02, id -> selectDao.selectUserObject02(id));
        map2.put(Constants.KEY_03, id -> selectDao.selectUserObject03(id));

        behaviourMap.put("getObj",map2);
        behaviourMap.put("getList",map);

        return behaviourMap;
    }


    private static Map<String,Object> generalSelectObj() {
        Map<String,Object> map = new HashMap<>();
        map.put("key",Constants.KEY_01);
        map.put("id",1L);
        return map;
    }

    private static Map<String,Object> generalSelectList() {
        Map<String,Object> map = new HashMap<>();
        map.put("key",Constants.KEY_04);

        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);

        map.put("ids",ids);
        return map;
    }

}