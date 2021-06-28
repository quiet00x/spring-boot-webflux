package com.example.webflux.service.report.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/21 - 23:54
 * @author: Mr_Bangb
 */
@Service
public abstract class AbstractReportService {
    private static final int AVAILABLE_PROCESSOR = Runtime.getRuntime().availableProcessors();
    /**
     * 固定大小线程池
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(AVAILABLE_PROCESSOR,
            2 * AVAILABLE_PROCESSOR,
            1, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("Report-ThreadPool-%d").build(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Resource
    private SqlSessionFactory sqlSessionFactory;


    public <T> Boolean insertBatch(List<T> list, Class clz) {
        return Boolean.TRUE;
    }

    public <T> Boolean updateBatch(List<T> list, Class clz) {
        return Boolean.TRUE;
    }

    public <T> Boolean deleteBatch(List<T> list, Class clz) {
        return Boolean.TRUE;
    }
}