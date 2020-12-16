package com.example.webflux.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:28
 * @author: Mr_Bangb
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBatchVO {
    private String updateType;
    private String category;
    private String jsonData;
}