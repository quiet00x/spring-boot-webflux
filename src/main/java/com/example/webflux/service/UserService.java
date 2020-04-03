package com.example.webflux.service;

import com.example.webflux.domain.User;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface UserService {
    List<User> selectAllUsers();
}