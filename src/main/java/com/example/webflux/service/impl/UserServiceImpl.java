package com.example.webflux.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webflux.domain.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}