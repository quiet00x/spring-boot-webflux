package com.example.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/8 - 0:29
 * @author: Mr_Bangb
 * 交易柜员实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeUserBean {

    private String trlUserCode;

    private String trlUserName;

    private String trlOrgCode;

    private String trlOrgName;

    private String trlBranchCode;

    private String trlBranchName;

    private String trlCityBranchCode;

    private String trlCityBranchName;
}