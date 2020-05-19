package com.example.webflux.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/15 - 0:36
 * @author: Mr_Bangb
 * 错误信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorInfoVO {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String errDetail;
}