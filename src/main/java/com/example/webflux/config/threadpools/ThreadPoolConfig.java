package com.example.webflux.config.threadpools;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/6/2 - 23:46
 * @author: Mr_Bangb
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * ThreadPoolExecutor构造参数
     *  1. corePoolSize	    int	     核心线程池大小
     *  2. maximumPoolSize	int	     最大线程池大小
     *  3. keepAliveTime	long	 线程最大空闲时间
     *  4. unit	            TimeUnit 时间单位
     *  5. workQueue	    BlockingQueue<Runnable>	    线程等待队列
     *  6. threadFactory	ThreadFactory	            线程创建工厂
     *  7. handler	        RejectedExecutionHandler	拒绝策略
     *
     *  参数说明：
     *  1.corePoolSize & maximumPoolSize
     *      a. execute(Runnable) 方法中提交新任务数量少于corePoolSize时，
     *      即使其他工作线程处于空闲状态，也会创建一个新线程来处理该请求。
     *      b. execute(Runnable) 方法中提交新任务数量多于corePoolSize但小于maximumPoolSize时，
     *      则仅当workQueue队列已满时才会创建新线程。
     *      c. 设置corePoolSize和maximumPoolSize相同，创建一个固定大小的线程池。
     *      d. maximumPoolSize设置为基本上无界的值，例如Integer.MAX_VALUE，
     *      可以允许池容纳任意数量的并发任务，但是禁止该操作。
     *  2.workQueue
     *      a. LinkedBlockingQueue(无界阻塞队列) 默认大小为Integer.MAX_VALUE会导致线程积压，禁止使用
     *
     * @see ThreadPoolExecutor
     * @return ThreadPoolExecutor
     */
    @Bean("fixedThreadPool")
    public ThreadPoolExecutor createFixedThreadPool() {
        return new ThreadPoolExecutor(4, 4,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("bangb-threadpool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

}