package com.example.webflux.common.utils;

import com.example.webflux.common.exception.LocalException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/22 - 0:56
 * @author: Mr_Bangb
 */
@Slf4j
public class MapperReflectUtils {

    /**
     * 通过方法名，以及容器管理的对象，利用反射执行对象的方法
     *
     * @param methodName 方法名
     * @param t          容器管理的对象
     * @param args       方法签名
     * @return 结果
     */
    public static <T> Object processMapperMethod(String methodName, T t, Object[] args) {
        Object result = null;
        Class<?> clz = t.getClass();
        try {
            log.info("----------------------> method:{} args:{}", methodName, args.toString());
            Method method = clz.getMethod(methodName, t.getClass());
            result = method.invoke(t, args);
        } catch (Exception e) {
            throw new LocalException(e.getMessage(), e);
        }
        return Optional.ofNullable(result).map(s -> s).orElse(Optional.empty());
    }
}

