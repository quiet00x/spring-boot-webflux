package com.example.webflux.service.pbi;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.vo.ResponseVO;
import com.example.webflux.vo.UpdateBatchVO;

import java.util.concurrent.ExecutionException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/6 - 0:32
 * @author: Mr_Bangb
 */
public interface IManageDetailService {
    ResponseVO<ResultEnum> updateNameBatch(UpdateBatchVO updateBatchVO) throws ExecutionException, InterruptedException;
    ResponseVO<ResultEnum> updatePropertyBatch(UpdateBatchVO updateBatchVO);
    ResponseVO<ResultEnum> updateNoBatch(UpdateBatchVO updateBatchVO);

}
