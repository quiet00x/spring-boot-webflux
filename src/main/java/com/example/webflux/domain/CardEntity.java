package com.example.webflux.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/7 - 23:41
 * @author: Mr_Bangb
 */
@Data
@TableName("report_card")
public class CardEntity extends BaseEntity{
    private String name;

    private Long originId;

    private Long datasetId;
}