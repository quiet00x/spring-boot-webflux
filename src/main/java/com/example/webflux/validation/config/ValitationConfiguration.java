package com.example.webflux.validation.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:53
 * @author: Mr_Bangb
 */
@Configuration
@Slf4j
public class ValitationConfiguration {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(
                HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        log.info("************************ validator is building ********************");
        return  validator;
    }
}