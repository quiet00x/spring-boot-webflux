package com.example.webflux.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webflux.domain.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}