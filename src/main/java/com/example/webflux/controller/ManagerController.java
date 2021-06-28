package com.example.webflux.controller;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.vo.UpdateBatchVO;
import com.example.webflux.service.pbi.IManageService;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:24
 * @author: Mr_Bangb
 */
@RequestMapping("/manager/")
@Slf4j
@RestController
public class ManagerController {
    @Resource
    private IManageService managerService;

    /**
     * 管理员工具：更新对象信息
     *
     * @param requestVO 请求参数
     * @return 成功标志
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseVO<ResultEnum> updateUtils(@RequestBody RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException {
        ResponseVO<ResultEnum> result = managerService.updateObjectBatch(requestVO);
        return result;
    }

    /**
     * 校验入参是否合法
     *
     * @param requestVO 请求参数
     * @return list
     */
    @RequestMapping(method = RequestMethod.POST, value = "checkInput")
    public ResponseVO<List<ResultEnum>> checkInputParams(@RequestBody RequestVO<UpdateBatchVO> requestVO) throws ExecutionException, InterruptedException {
        Optional.ofNullable(requestVO).orElseThrow(LocalException::new);
        Optional.ofNullable(requestVO.getInput()).orElseThrow(LocalException::new);
        ResponseVO<List<ResultEnum>> result = managerService.checkInputParams(requestVO);
        return result;
    }

}