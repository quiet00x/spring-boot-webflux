package com.example.webflux.service.impl;

import com.example.webflux.common.constant.ReqConstant;
import com.example.webflux.domain.CreditBaseBean;
import com.example.webflux.service.BV6070Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepositCreditServiceImpl extends BaseCreditProofServiceImpl{
	
	@Autowired
	private BV6070Service bv6070ServiceImpl;

	/**
	 * 开立存款证明
	 * @param printType 打印类型
	 * @return base
	 */
	public CreditBaseBean openCreditProof(CreditBaseBean creditBaseBean, String printType) {
		Map<String, Object> register = bv6070ServiceImpl.registerDepositCredit(creditBaseBean);
		Map<String, Object> resultMap = bv6070ServiceImpl.parseRegisterRet(register);
		
		List<String> registerNoList = (List<String>) resultMap.get(ReqConstant.CreditProof.REGISTER_NO_LIST_PARAM);
		String creditNo = (String) resultMap.get(ReqConstant.CreditProof.CREDIT_NO_PARAM);
		
		creditBaseBean.setCreditNo(creditNo);
		creditBaseBean.setRegisterNoList(registerNoList.toString());
		
		
		return creditBaseBean;
	}

	

}
