package com.example.webflux.service;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:21
 * @author: Mr_Bangb
 */
public interface CreditProofService {

    /**
     *
     * @param map
     * @return
     */
    public Map<String, Object> openCreditProof(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    public Map<String, Object> openTradeProof(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    public Map<String, Object> queryCusOpenHis(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    public Map<String, Object> resendEmail(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    public Map<String, Object> queryCreditProofInfo(Map<String, Object> map);
}
