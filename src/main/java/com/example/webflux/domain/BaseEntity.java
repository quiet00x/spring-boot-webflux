package com.example.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/7 - 23:42
 * @author: Mr_Bangb
 */
@Data
public class BaseEntity {
    private Long id;

    private String status;

    private String createdBy;

    private String updatedBy;

    private String createDate;

    private String updateDate;

    private String dr;
}