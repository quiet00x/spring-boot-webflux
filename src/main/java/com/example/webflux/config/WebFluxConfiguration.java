package com.example.webflux.config;

import com.example.webflux.domain.User;
import com.example.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:48
 * @author: Mr_Bangb
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctioUsers(UserRepository userRepository) {
        // 获取所有的userList
        Collection<User> userList = userRepository.findAll();
        // 创建Flux 包含所有的userList
        Flux<User> userListFlux = Flux.fromIterable(userList);
        // 利用RouterFunction 进行路由
        return RouterFunctions.route(RequestPredicates.path("/users"),
                /*
                mono 0 -1 个 元素
                flux 0 - N 个 元数
                 */
                request -> ServerResponse.ok().body(userListFlux, User.class));
    }
}