package com.example.webflux.service.pbi.impl;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.vo.UpdateBatchVO;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:58
 * @author: Mr_Bangb
 */
@Service
public class PropertyManagerService extends ManagerService {
    public ResponseVO<ResultEnum> updateBatch(RequestVO<UpdateBatchVO> requestVO) {
        System.out.println("PropertyManagerService.updateObjectBatch");
        return null;
    }

    public ResponseVO<List<ResultEnum>> checkInputParams(RequestVO<UpdateBatchVO> requestVO) {
        System.out.println("PropertyManagerService.checkInputParams");
        return null;
    }
}