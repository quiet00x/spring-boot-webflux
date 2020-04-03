package com.example.webflux.controller;

import com.example.webflux.domain.User;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:49
 * @author: Mr_Bangb
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final AtomicLong idGenerator = new AtomicLong();
    @Autowired
    private UserService userServiceImpl;

    /**
     * 构造器自动注入repository
     * Autowire可以使用在方法上
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/user/save")
    public User saveUser(@RequestParam String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setId(idGenerator.incrementAndGet());
        userRepository.save(user);
        return user;
    }

    @RequestMapping("/user/selectAllUser")
    public List<User> selectAllUsers() {

        List<User> userList = userServiceImpl.selectAllUsers();

        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        } else {
            return userList;
        }
    }

}