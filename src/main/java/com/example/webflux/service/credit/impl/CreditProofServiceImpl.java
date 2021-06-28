package com.example.webflux.service.credit.impl;

import com.example.webflux.service.credit.CreditProofService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:21
 * @author: Mr_Bangb
 */
@Service
public class CreditProofServiceImpl extends BaseCreditProofServiceImpl implements CreditProofService {

    @Override
    public Map<String, Object> openCreditProof(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> openTradeProof(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> queryCusOpenHis(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> resendEmail(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> queryCreditProofInfo(Map<String, Object> map) {
        return null;
    }
}