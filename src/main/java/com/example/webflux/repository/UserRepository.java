package com.example.webflux.repository;

import com.example.webflux.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:45
 * @author: Mr_Bangb
 */
@Repository
public class UserRepository {
    /**
     * 支持并发的HashMap
     */
    private final ConcurrentMap<Long, User> repository = new ConcurrentHashMap<>();

    /**
     * 原子类，原理是CAS
     */
    private final AtomicLong idGenerator = new AtomicLong();

    public Boolean save(User user) {
        // ID 自增
        long id = idGenerator.incrementAndGet();

        // 如果同一个id的user发生变化，则会返回上一次的user对象
        // 返回null表示没有改动
        return repository.put(id,user) == null;
    }

    /**
     * 返回集合中所有的对象
     * @return 返回的是view 只读
     */
    public Collection<User> findAll() {
        return repository.values();
    }

}