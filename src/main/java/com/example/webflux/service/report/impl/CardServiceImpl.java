package com.example.webflux.service.report.impl;

import com.example.webflux.service.report.ICardService;
import com.example.webflux.service.report.IReportService;
import com.example.webflux.vo.CardVo;
import com.example.webflux.vo.ConfigDataVo;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:44
 * @author: Mr_Bangb
 */
@Service
@Slf4j
public class CardServiceImpl extends AbstractReportService implements ICardService, IReportService {


    @Override
    public List<CardVo> exportReport(CardVo cardVo) {
        return null;
    }

    @Override
    public ResponseVO importReport(RequestVO<ConfigDataVo> requestVO) {
        return null;
    }
}