package com.example.webflux.service.report;

import com.example.webflux.vo.ConfigDataVo;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
public interface IReportService {
    /**
     * 配置项导入
     *
     * @param requestVO 配置项
     * @return
     */
    ResponseVO importReport(RequestVO<ConfigDataVo> requestVO);

}