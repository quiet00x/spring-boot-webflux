package com.example.webflux.vo;

import com.example.webflux.domain.TraceInfoBean;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/15 - 0:30
 * @author: Mr_Bangb
 */
@Data
@ToString
public class RequestVO <T> {
    /**
     * 公共交易信息
     */
    private TraceInfoBean traceInfo;
    /**
     * 请求数据
     */
    private T input;
    /**
     * 分页对象
     */
    private PageInfoVO pageInfoVO;

}