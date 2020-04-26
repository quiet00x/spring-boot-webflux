package com.example.webflux.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.response.ResponseResult;
import com.example.webflux.domain.UserBean;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:49
 * @author: Mr_Bangb
 */
@RestController
@Slf4j
@RequestMapping("/user/")
public class UserController {

    private final UserRepository userRepository;

    private final AtomicLong idGenerator = new AtomicLong();

    @Autowired
    private UserService userServiceImpl;

    @Resource(name="dictProperties")
    private Properties dictProperties;

    @Resource(name="versionProperties")
    private Properties versionProperties;

    @Resource(name="properties")
    private Properties properties;

    /**
     * 构造器自动注入repository
     * Autowire可以使用在方法上
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Mybatis-plus 插件使用demo insert
     * @param userName
     * @return
     */
    @RequestMapping("save")
    public UserBean saveUser(@RequestParam String userName) {
        UserBean user = new UserBean();
        user.setUserName(userName);

        if (userServiceImpl.save(user)) {
            userRepository.save(user);
        }
        return user;
    }

    /**
     * Mybatis-plus 插件使用demo selectAll
     * @return
     */
    @RequestMapping("list")
    public List<UserBean> selectAllUsers() {

        List<UserBean> userList = userServiceImpl.list();

        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        } else {
            return userList;
        }
    }

    /**
     * Mybatis-plus 插件使用demo update
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResponseResult updateUser(UserBean user) {

        if (userServiceImpl.update(user
                ,new UpdateWrapper<UserBean>()
                        .eq("id",17))) {
            return new ResponseResult(ResultEnum.SUCCESS);
        } else {
            return new ResponseResult(ResultEnum.FAILE);
        }
    }

    /**
     * 背景：
     *  容器启动 加载 properties文件，构建数据字典
     * @param user
     */
    @RequestMapping("load")
    public void load(UserBean user) {

       log.info(dictProperties.get("userName").toString());

       log.info(versionProperties.get("projectVersion").toString());

        log.info(properties.getProperty("userId"));
    }

    /**
     * 背景：
     *  调试 Mybatis 分页插件 foreach 丢失list参数
     * @param userBean
     * @return
     */
    @RequestMapping(value = "getUsers",method = RequestMethod.POST)
    public List<UserBean> getUsersByIds(@RequestBody UserBean userBean) {
        log.info("-------------------> userBean:{}",userBean);

        List<UserBean> users= userServiceImpl.mySelectUsers(userBean);

        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        } else {
            return users;
        }
    }

    /**
     * 背景：
     *  1. 不同的条件，执行不同的dao层方法
     *  2. 条件类别很多，需要用到大量的 if else if
     *  3. 每个dao方法涉及的表不同
     * @param userBean
     * @return
     */
    @RequestMapping(value = "getUsersByCondition",method = RequestMethod.POST)
    public UserBean getUsersByCondition(@RequestBody UserBean userBean) {
        log.info("-------------------> userBean:{}",userBean);

        UserBean user= userServiceImpl.selectUsersByCondition(userBean);

        return Optional.ofNullable(user).orElse(new UserBean());
    }

}