package com.example.webflux.controller;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.domain.CreditBaseBean;
import com.example.webflux.service.CreditProofService;
import com.example.webflux.vo.ResponseVO;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:18
 * @author: Mr_Bangb
 * 利用设计模式，完成资信证明开立需求的改造
 * 1. Template 模式 约束行为
 * 2. Builder模式 隐藏复杂属性的构造
 * 3. Bridge 模式 使得类的层次结构以及实现层次清晰
 * 4. 还可以利用Factory模式 生成一个工厂代理对象
 * 5. proxy模式
 */
@RestController
@RequestMapping("credit/")
public class CreditProofController {

    @Autowired
    private CreditProofService creditProofImpl;

    @RequestMapping("openTradeProof")
    public ResponseVO openTradeProof(Map<String, Object> params){
        Map<String, Object> resultMap = creditProofImpl.openTradeProof(params);

        if (CollectionUtils.isEmpty(resultMap)) {
            return new ResponseVO(ResultEnum.SUCCESS);
        } else {
            return new ResponseVO(ResultEnum.FAILE);
        }
    }

    @RequestMapping("openCreditProof")
    public ResponseVO openCreditProof(Map<String, Object> params){
        Map<String, Object> resultMap = creditProofImpl.openCreditProof(params);

        if (CollectionUtils.isEmpty(resultMap)) {
            return new ResponseVO(ResultEnum.SUCCESS);
        } else {
            return new ResponseVO(ResultEnum.FAILE);
        }
    }


    @RequestMapping("queryCusOpenHis")
    public ResponseVO<CreditBaseBean> queryCusOpenHis(Map<String, Object> params){
        Map<String, Object> httpResult = creditProofImpl.queryCusOpenHis(params);
        return null;
    }

    @RequestMapping("resendEmail")
    @ResponseBody
    @JsonView
    public Map<String, Object> resendEmail(Map<String, Object> params){
        Map<String, Object> httpResult = creditProofImpl.resendEmail(params);
        return httpResult;
    }


    @RequestMapping("queryCreditProofInfo")
    public Map<String, Object> queryCreditProofInfo(Map<String, Object> params){
        Map<String, Object> httpResult = creditProofImpl.queryCreditProofInfo(params);
        return httpResult;
    }

}