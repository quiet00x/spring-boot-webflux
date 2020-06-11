package com.example.webflux.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.domain.UserBean;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.UserService;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:49
 * @author: Mr_Bangb
 * @description 采用restFul请求风格（互联网所有的事物都可以被抽象为资源）
 *  1. GET
 *      查询服务器资源
 *  2. POST
 *      创建服务器资源
 *  3. PUT
 *      更新服务端的资源的全部信息
 *  4. PATCH
 *      更新服务端的资源的部分信息
 *  5. DELETE
 *      删除服务端的资源
 *
 * API设计风格基本规则
 *
 * 1.使用名词而不是动词
 * 2.Get方法和查询参数不应该涉及状态改变
 * 3.使用复数名词
 * 4.使用子资源表达关系
 *      demo: GET /cars/711/drivers/4 返回 car 711的4号司机
 * 5.使用Http头声明序列化格式
 *      5.1 Content-Type 定义请求格式
 *      5.2 Accept 定义系列可接受的响应格式
 * 6.为集合提供过滤 排序 选择和分页等功能
 * 7.版本化你的API
 * 8. 使用Http状态码处理错误
 *      200 – OK – 一切正常
 *      201 – OK – 新的资源已经成功创建
 *      204 – OK – 资源已经成功擅长
 *      304 – Not Modified – 客户端使用缓存数据
 *      400 – Bad Request – 请求无效，需要附加细节解释如 "JSON无效"
 *      401 – Unauthorized – 请求需要用户验证
 *      403 – Forbidden – 服务器已经理解了请求，但是拒绝服务或这种请求的访问是不允许的。
 *      404 – Not found – 没有发现该资源
 *      422 – Unprocessable Entity – 只有服务器不能处理实体时使用，比如图像不能被格式化，或者重要字段丢失。
 *      500 – Internal Server Error – API开发者应该避免这种错误。
 */

@RequestMapping("/users/")
@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userServiceImpl;

    private final UserRepository userRepository;
    /**
     * 构造器注入 repository
     *
     * @param userRepository repository
     */
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Resource(name="fixedThreadPool")
    private ThreadPoolExecutor service;

    /**
     * Mybatis-plus 插件使用demo selectAll
     * 查询所有users
     *
     * @return users
     * @description restFul风格请求，GET表示查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseVO<List<UserBean>> selectAllUsers() {

        List<UserBean> userList = userServiceImpl.list();

        if (CollectionUtils.isEmpty(userList)) {
            userList = Collections.emptyList();
        }
        return ResponseVO.buildSuccess(userList);
    }

    /**
     * 背景：
     *  调试 Mybatis 分页插件 foreach 丢失list参数
     * @param sex 性别
     * @param ids id列表以‘,'分隔
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "sex/{sex}/{ids}")
    public ResponseVO<List<UserBean>> getUsersByIds(
            @PathVariable("sex") String sex, @PathVariable("ids") String ids) {
        log.info("-------------------> sex:{},ids:{}",sex,ids);

        List<Long> idList = new ArrayList<>();
        if (StringUtils.isNotBlank(ids)) {
            String[] split = StringUtils.split(ids, ',');
            Arrays.stream(split).forEach(id -> idList.add(Long.valueOf(id)));
        }
        UserBean user = UserBean.builder().sex(sex).ids(idList).build();
        List<UserBean> users= userServiceImpl.mySelectUsers(user);

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
     * @param id userId
     * @return user
     * @Description 使用事务的原因：
     *  1. Mybatis一级缓存作用效范围是session（默认）或statment；
     *  2. 数据库连接池，默认每次查询完之后自动commite，会导致两次查询使用的不是同一个sqlSessioin
     *  3. 不同的sqlSession一级缓存永远不会命中
     */
    @Transactional
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseVO<UserBean> getUsersById(@PathVariable("id") String id) {
        log.info("-------------------> id:{}", id);

        UserBean user = userServiceImpl.getById(id);

        UserBean user1 = userServiceImpl.getById(id);

        return ResponseVO.buildSuccess(user);
    }

    /**
     * 背景：
     *  1. 不同的条件，执行不同的dao层方法
     *  2. 条件类别很多，需要用到大量的 if else if
     *  3. 每个dao方法涉及的表不同
     * @param userBean
     * @return
     */
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public ResponseVO<UserBean> getUsersByCondition(@RequestBody UserBean userBean) {
        log.info("-------------------> userBean:{}", userBean);

        UserBean user= userServiceImpl.selectUsersByCondition(userBean);

        return ResponseVO.buildSuccess(user);
    }

    /**
     * Mybatis-plus 插件使用demo insert
     *
     * @param users 新增用户列表
     * @return ResponseVO
     * @description restFul风格请求，POST表示新增
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO<UserBean> saveUser(@RequestBody List<UserBean> users) {
        ResponseVO<UserBean> responseVO;
        if (CollectionUtils.isEmpty(users)) {
            responseVO = ResponseVO.buildSuccess(Collections.emptyList());
        }

        if (userServiceImpl.saveBatch(users)) {
            responseVO = ResponseVO.buildSuccess(users);
        } else {
            responseVO = ResponseVO.buildErrorByResultEnum(ResultEnum.FAILED_PARAMETER_VALUE_ERROR);
        }

        return responseVO;
//        for(int i = 0; i < 4; i++) {
//            service.execute(() -> userServiceImpl.saveBatch(generalUsers(500000)));
//            log.info("*****************************************************");
//        }
    }

    private List<UserBean> generalUsers(int num) {
        List<UserBean> users = new ArrayList<>(num + 1);
        for(int i = 0; i <= num; i++) {
            String uuid = UUID.randomUUID().toString().substring(19);
            UserBean userTemp = UserBean.builder()
                    .userName(uuid)
                    .sex(generalSex())
                    .age(String.valueOf(new Random().nextInt(30) +1))
                    .birthday(new Date())
                    .password(uuid)
                    .salary(new BigDecimal(String.valueOf(new Random().nextInt(9999)))).build();
            users.add(userTemp);
        }
        return users;
    }

    private String generalSex(){
        StringBuilder builder = new StringBuilder("0");
        int i = new Random().nextInt(1) + 1;
        return builder.append(i).toString();
    }

    /**
     * Mybatis-plus 插件使用demo update
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseVO<UserBean> updateUser(@RequestBody UserBean user) {
        Boolean successFlag = userServiceImpl.update(
                user,new UpdateWrapper<UserBean>()
                        .eq("id",user.getId()));
        if (successFlag) {
            return ResponseVO.buildSuccessWithoutData(ResultEnum.SUCCESS);
        } else {
            return ResponseVO.buildErrorByResultEnum((ResultEnum.FAILED_PARAMETER_VALUE_ERROR));
        }
    }
}