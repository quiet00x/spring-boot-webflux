package com.example.webflux.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webflux.common.constant.TransCodeConstant;
import com.example.webflux.common.constant.ResponseConstant;
import com.example.webflux.common.enums.MapperEnum;
import com.example.webflux.common.enums.TableEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.common.utils.MapperReflectUtils;
import com.example.webflux.domain.UserBean;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserBean> implements UserService {

    private static Map<String, String> TRANS_CODE_MAP = new HashMap<>();

    static {
        TRANS_CODE_MAP = TableEnum.converToMap();
    }

    @Resource
    private UserMapper userMapper;

    /**
     * 用户列表查询
     * @param userBeans
     * @return
     */
    @Override
    public List<UserBean> mySelectUsers(UserBean userBeans) {
        return userMapper.mySelectUsers(userBeans);
    }

    /**
     * 各个部门的用户信息查询
     * 业务场景，数据量很大，采用分表策略，一个部门一张表,表字段相差
     * @param userSerachVo
     * @return
     */
    @Override
    public UserBean selectUsersByCondition(UserBean userSerachVo) {
        String transCode = userSerachVo.getTransCode();

        // 常规写法
        UserBean user;
        if (StringUtils.equals(TransCodeConstant.SELECT_SALES_DEPARTMENT_USER,transCode)) {
            user = userMapper.selectSalesUsers(userSerachVo);
        } else if (StringUtils.equals(TransCodeConstant.SELECT_ADMIN_DEPARTMENT_USER,transCode)) {
            user =userMapper.selectAdminUsers(userSerachVo);
        } else if (StringUtils.equals(TransCodeConstant.SELECT_PER_DEPARTMENT_USER,transCode)) {
            user =userMapper.selectPerUsers(userSerachVo);
        } else if (StringUtils.equals(TransCodeConstant.SELECT_PUR_DEPARTMENT_USER,transCode)) {
            user =userMapper.selectPurUsers(userSerachVo);
        } else {
            throw new LocalException(ResponseConstant.ILLEGAL_ARGUMENT_EXCEPTION_CODE,"ERROR_TRANS_CODE",transCode);
        }

        /*
         改进版本1 : 将表名作为参数 传入SQL
         适用场景：
            1. 表结构类似
            2. SQL类似
         好处：
            1. 减少了 mapper 方法 以及 SQL
          注意：表名不能由前端输入，避免SQL注入风险
          缺点：
            1. 维护性能差，无法通过SQL获知是对那张表进行操作
            2. 需要单独建立 表名与transCode的映射关系
         */
        Optional.ofNullable(TRANS_CODE_MAP.get(transCode)).ifPresent(s -> userSerachVo.setTableName(s));

        user = userMapper.selectUserByTransCode(userSerachVo);

        /*
         改进版本2: 利用反射，通过方法名，以及对象 反射执行对应的方法
         适用场景：
            1. 表结构以及SQL差异大
         好处：
            1. 方法十分通用
         注意：invoke方法的object对象不能通过字节码获取，应传入容器管理的对象，否则导致事务失效
         缺点:
            1. 维护性能相对还是不好
            2. 需要单独建立 表名与transCode的映射关系
            3. 性能相对较差
         */
        // 获取方法名
        String methodName = MapperEnum.getValueByKey(transCode).get();
        // 获取对象字节码
        Object result = MapperReflectUtils.processMapperMethod(methodName,userMapper,new Object[]{userSerachVo});

        /*
         改进版本3：利用 java多态特性，抽象父 Mapper，子类重写父类 方法
         适用场景：
            1. Mapper 方法较多
            2. 每段sql相差较大且复杂
         优点：
            1. 结构清晰
            2. 无需单独维护映射关系
         缺点：
            1. 类多
            2. Mapper.xml多
         */

        /*
        改进版本4：利用函数式编程，抽象一个函数式接口
        适用场景：
            1. 判断条件相同，返回结果相同
            2. 单一方法中判断条件过多
        缺点：
            1. 依赖于函数式接口，返回列表需要一个接口，返回对象又需要一个接口
            2. Mapper 方法返回的参数类型不一致，导致Map很多
            3. 性能相对较差
         */

        return Optional.ofNullable(user).map(u -> u).orElse(new UserBean());
    }
}