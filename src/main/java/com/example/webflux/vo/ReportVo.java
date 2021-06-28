package com.example.webflux.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/21 - 23:10
 * @author: Mr_Bangb
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportVo extends ReportBaseVo {

}