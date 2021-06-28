package com.example.webflux.controller;

import com.example.webflux.service.report.IReportService;
import com.example.webflux.vo.ConfigDataVo;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/21 - 23:04
 * @author: Mr_Bangb
 */
@RestController
@RequestMapping("/report/")
public class ReportController {
    @Resource
    private IReportService reportService;

    /**
     * 配置项导入
     *
     * @param requestVO 入参
     * @return 操作结果
     */
    @RequestMapping(method = RequestMethod.POST, value = "import")
    public ResponseVO importReport(RequestVO<ConfigDataVo> requestVO) {
        return reportService.importReport(requestVO);
    }
}