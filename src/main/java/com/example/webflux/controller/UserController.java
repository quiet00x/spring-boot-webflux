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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:49
 * @author: Mr_Bangb
 */
@RestController
@Slf4j
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

    @RequestMapping("/user/save")
    public UserBean saveUser(@RequestParam String userName) {
        UserBean user = new UserBean();
        user.setUserName(userName);

        if (userServiceImpl.save(user)) {
            userRepository.save(user);
        }
        return user;
    }

    @RequestMapping("/user/list")
    public List<UserBean> selectAllUsers() {

        List<UserBean> userList = userServiceImpl.list();

        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        } else {
            return userList;
        }
    }

    @RequestMapping("/user/update")
    public ResponseResult updateUser(UserBean user) {

        if (userServiceImpl.update(user
                ,new UpdateWrapper<UserBean>()
                        .eq("id",17))) {
            return new ResponseResult(ResultEnum.SUCCESS);
        } else {
            return new ResponseResult(ResultEnum.FAILE);
        }
    }

    @RequestMapping("/user/load")
    public void load(UserBean user) {

       log.info(dictProperties.get("userName").toString());

       log.info(versionProperties.get("projectVersion").toString());

        log.info(properties.getProperty("userId"));
    }

}