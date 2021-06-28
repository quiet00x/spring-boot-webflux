package com.example.webflux.service.report.impl;

import com.example.webflux.common.constant.MagicConstant;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.service.report.IReportService;
import com.example.webflux.vo.ConfigDataVo;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 21:43
 * @author: Mr_Bangb
 */
@Service
@Slf4j
public class ReportServiceImpl implements IReportService {

    @Override
    public ResponseVO importReport(RequestVO<ConfigDataVo> requestVO) {
        ConfigDataVo input = requestVO.getInput();
        switch (input.getImportModel()) {
            case MagicConstant.IMPORT_MODEL_INIT:
                initImportReport(input);
                break;
            case MagicConstant.IMPORT_MODEL_INCREMENT:
                incrementImportReport(input);
                break;
            case MagicConstant.IMPORT_MODEL_ONLY_INCREMENT:
                onlyIncrementImportReport(input);
                break;
            default:
                throw new LocalException(ResultEnum.FAILED_PARAMETER_VALUE_ERROR);
        }
        return null;
    }

    private void onlyIncrementImportReport(ConfigDataVo input) {

    }

    private void incrementImportReport(ConfigDataVo input) {
    }

    private void initImportReport(ConfigDataVo input) {
    }
}