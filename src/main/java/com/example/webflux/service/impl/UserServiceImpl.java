package com.example.webflux.service.impl;

import com.example.webflux.domain.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectList(null);
    }
}