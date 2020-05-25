package com.example.webflux.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/15 - 0:32
 * @author: Mr_Bangb
 * 分页对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoVO {
    /**
     * 当前页
     */
    private Integer curPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer totalRows;
    /**
     * 总页数
     */
    private Integer totalPages;
}