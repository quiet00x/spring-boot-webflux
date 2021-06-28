package com.example.webflux.service.report.impl;

import com.example.webflux.service.report.IBoardService;
import com.example.webflux.service.report.IReportService;
import com.example.webflux.vo.BoardVo;
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
public class BoardServiceImpl extends AbstractReportService implements IBoardService, IReportService {
    @Override
    public ResponseVO importReport(RequestVO<ConfigDataVo> requestVO) {
        return null;
    }

    @Override
    public List<BoardVo> exportReport(BoardVo cardVo) {
        return null;
    }
}