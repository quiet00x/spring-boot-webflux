package com.example.webflux.service.credit;

import com.example.webflux.domain.CreditBaseBean;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:25
 * @author: Mr_Bangb
 */
public interface BV6070Service {

    public Map<String, Object> registerDepositCredit(CreditBaseBean creditBaseBean);

    public Map<String, Object> parseRegisterRet(Map<String, Object> register);
}
