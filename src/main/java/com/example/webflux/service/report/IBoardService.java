package com.example.webflux.service.report;

import com.example.webflux.vo.BoardVo;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface IBoardService {
    List<BoardVo> exportReport(BoardVo cardVo);
}