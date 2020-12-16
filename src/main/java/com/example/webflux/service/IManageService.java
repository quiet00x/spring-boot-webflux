package com.example.webflux.service;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.vo.UpdateBatchVO;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:27
 * @author: Mr_Bangb
 */
public interface IManageService extends IManageDetailService{
    /**
     * 批量更新对象
     *
     * @param requestVO 待更新资源
     * @return result
     */
    ResponseVO<ResultEnum> updateObjectBatch(RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException;

    /**
     * 参数合法性校验
     *
     * @param requestVO 待更新资源
     * @return list
     */
    ResponseVO<List<ResultEnum>> checkInputParams(RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException;
}