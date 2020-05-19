package com.example.webflux.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.domain.UserBean;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.UserService;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
    public ResponseVO<UserBean> saveUser(@RequestParam String userName) {
        UserBean user = new UserBean();
        user.setUserName(userName);

        if (userServiceImpl.save(user)) {
            userRepository.save(user);
        }

        return ResponseVO.buildSuccess(null);
    }

    /**
     * Mybatis-plus 插件使用demo selectAll
     * @return
     */
    @RequestMapping("list")
    public ResponseVO<List<UserBean>> selectAllUsers() {

        List<UserBean> userList = userServiceImpl.list();

        if (CollectionUtils.isEmpty(userList)) {
            userList = Collections.emptyList();
        }

        return ResponseVO.buildSuccess(userList);
    }

    /**
     * Mybatis-plus 插件使用demo update
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResponseVO<UserBean> updateUser(UserBean user) {

        if (userServiceImpl.update(user
                ,new UpdateWrapper<UserBean>()
                        .eq("id",17))) {
            return new ResponseVO(ResultEnum.SUCCESS);
        } else {
            return new ResponseVO(ResultEnum.FAILE);
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

        Optional.ofNullable(null).orElseThrow(LocalException::new);
    }

    /**
     * 背景：
     *  调试 Mybatis 分页插件 foreach 丢失list参数
     * @param userBean
     * @return
     */
    @RequestMapping(value = "getUsers",method = RequestMethod.POST)
    public ResponseVO<List<UserBean>> getUsersByIds(@RequestBody UserBean userBean) {
        log.info("-------------------> userBean:{}",userBean);

        List<UserBean> users= userServiceImpl.mySelectUsers(userBean);

        if (CollectionUtils.isEmpty(users)) {
            return ResponseVO.buildSuccess(Collections.emptyList());
        } else {
            return ResponseVO.buildSuccess(users);
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
    public ResponseVO<UserBean> getUsersByCondition(@RequestBody UserBean userBean) {
        log.info("-------------------> userBean:{}",userBean);

        UserBean user= userServiceImpl.selectUsersByCondition(userBean);

        return ResponseVO.buildSuccess(user);
    }

}