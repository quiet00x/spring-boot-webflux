package com.example.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/15 - 0:05
 * @author: Mr_Bangb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TraceInfoBean {
    private TradeUserBean tradeUserBean;
    private String traceNo;
    private String clientIp;
    private String serverIp;
}