package com.example.webflux.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/21 - 23:09
 * @author: Mr_Bangb
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportBaseVo {
    private Long id;

    private String dr;

    private String createdBy;

    private String updatedBy;

    private String createDate;

    private String updateDate;
}