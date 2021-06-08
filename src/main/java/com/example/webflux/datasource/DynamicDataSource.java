package com.example.webflux.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/13 - 23:09
 * @author: Mr_Bangb
 * @description 仿照renren-fast多数据源实现
 * 利用 AbstractRoutingDataSource 实现动态动态多数据源
 * 多数据源使用场景：
 *      针对跨多个数据库实例的情况。
 *      如果是同个数据库实例中有多个数据库，则没必要使用多数据源。
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 线程本地变量，存放当前线程数据源
     */
    private static final ThreadLocal<String> CONTEXT_HANDLER = new ThreadLocal<>();

    /**
     * 利用构造方法 设置默认数据源
     * @param defaultTargetDataSource
     */
    public DynamicDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();

    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    private void setDataSource(String dataSource){
        CONTEXT_HANDLER.set(dataSource);
    }

    private String getDataSource() {
        return CONTEXT_HANDLER.get();
    }

    private void removeDataSource(){
        CONTEXT_HANDLER.remove();
    }

}