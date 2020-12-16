package com.example.webflux.service.impl;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.service.IManageDetailService;
import com.example.webflux.vo.ResponseVO;
import com.example.webflux.vo.UpdateBatchVO;

import java.util.concurrent.ExecutionException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/6 - 0:35
 * @author: Mr_Bangb
 */
public class ManageDetailService extends ManagerBaseService implements IManageDetailService {
    @Override
    public ResponseVO<ResultEnum> updateNameBatch(UpdateBatchVO updateBatchVO) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public ResponseVO<ResultEnum> updatePropertyBatch(UpdateBatchVO updateBatchVO) {
        return null;
    }

    @Override
    public ResponseVO<ResultEnum> updateNoBatch(UpdateBatchVO updateBatchVO) {
        return null;
    }
}