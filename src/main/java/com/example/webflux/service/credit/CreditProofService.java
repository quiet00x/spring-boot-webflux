package com.example.webflux.service.credit;

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
    Map<String, Object> openTradeProof(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Map<String, Object> openCreditProof(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Map<String, Object> queryCusOpenHis(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Map<String, Object> resendEmail(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Map<String, Object> queryCreditProofInfo(Map<String, Object> map);
}
