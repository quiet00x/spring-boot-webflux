package com.example.webflux.service.impl;

import com.example.webflux.common.enums.ManagerImplEnum;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.vo.UpdateBatchVO;
import com.example.webflux.service.IManageService;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:57
 * @author: Mr_Bangb
 */
@Service
public class ManagerService extends ManageDetailService implements IManageService {

    @Override
    public ResponseVO<ResultEnum> updateObjectBatch(RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException {
        String category = requestVO.getInput().getCategory();
        IManageService managerServiceImpl = getManageServiceImpl(category);
        managerServiceImpl.updateNameBatch(requestVO.getInput());
        return null;
    }

    @Override
    public ResponseVO<List<ResultEnum>> checkInputParams(RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException {
        String operType = requestVO.getInput().getCategory();
        IManageService managerServiceImpl = getManageServiceImpl(operType);
        managerServiceImpl.updateObjectBatch(requestVO);
        return null;
    }
}