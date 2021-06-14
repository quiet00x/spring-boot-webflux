package com.example.webflux.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.domain.CardEntity;
import com.example.webflux.mapper.CardMapper;
import com.example.webflux.service.ICardService;
import com.example.webflux.vo.CardVo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
@Slf4j
public class CardServiceImpl extends ServiceImpl<CardMapper, CardEntity> implements ICardService {
    /**
     * 固定大小线程池
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("Card-ThreadPool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Resource
    private CardMapper cardMapper;

    @Override
    public List<CardEntity> exportMockito(CardVo cardVo) {
        CompletableFuture<List<CardEntity>> future1 = CompletableFuture.supplyAsync(() -> exportMockito(Arrays.asList(1L, 2L)));
        CompletableFuture<List<CardEntity>> future2 = CompletableFuture.supplyAsync(() -> exportMockito(Arrays.asList(3L, 5L)));
        CompletableFuture<List<CardEntity>> future3 = CompletableFuture.supplyAsync(() -> exportExceptionMockito(Arrays.asList(5L, 8L)));
        return collectResult(future1, future2, future3);
    }

    private List<CardEntity> collectResult(CompletableFuture<List<CardEntity>> future1, CompletableFuture<List<CardEntity>> future2, CompletableFuture<List<CardEntity>> future3) {
        List<CardEntity> result = new ArrayList<>();
        try {
            result.addAll(future1.get());
            result.addAll(future2.get());
            result.addAll(future3.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("====>>====>>errorMsg is {}", e.getMessage());
            throw new LocalException(e.getMessage());
        }
        return result;
    }

    private List<CardEntity> exportMockito(List<Long> ids) {
        return (List<CardEntity>) listByIds(ids);
    }

    private List<CardEntity> exportExceptionMockito(List<Long> ids) {
        throw new LocalException(ResultEnum.FAILED_UNKNOWN_ERROR);
    }
}