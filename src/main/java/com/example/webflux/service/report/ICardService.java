package com.example.webflux.service.report;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.webflux.domain.CardEntity;
import com.example.webflux.domain.CategoryBean;
import com.example.webflux.domain.ExtendBean;
import com.example.webflux.domain.UserBean;
import com.example.webflux.vo.CardVo;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface ICardService {
    List<CardVo> exportReport(CardVo cardVo);
}