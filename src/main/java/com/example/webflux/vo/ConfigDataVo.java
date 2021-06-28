package com.example.webflux.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/21 - 23:22
 * @author: Mr_Bangb
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigDataVo {
    private String importModel;

    private List<CardVo> cardVos;

    private List<BoardVo> boardVos;
}