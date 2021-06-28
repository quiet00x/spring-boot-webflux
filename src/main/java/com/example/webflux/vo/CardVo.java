package com.example.webflux.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/7 - 23:50
 * @author: Mr_Bangb
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardVo extends ReportBaseVo {
    private String name;

    private String categoryId;

    private Long originId;

    private Long datasetId;

    private String status;
}