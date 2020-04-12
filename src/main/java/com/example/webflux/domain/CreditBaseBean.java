package com.example.webflux.domain;

import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:27
 * @author: Mr_Bangb
 * 线上开立凭证通用属性实体类
 */
@Data
public class CreditBaseBean {

    private String openNo;

    private String creditNo;

    private String registerNoList;

    private String creditType;

    private String cusNo;

    private String cusName;

    private String email;

    private String openDate;

    private String openOrgCode;

    private String openOrgName;

    private String openBranchCode;

    private String openBranchName;

    private TradeUserBean trlUserBean;

    private String dealStatus;

    private String errMsg;
}