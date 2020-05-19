package com.example.webflux.vo;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.domain.TraceInfoBean;
import lombok.Data;
import lombok.ToString;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:57
 * @author: Mr_Bangb
 */
@Data
@ToString
public class ResponseVO<T> {
    /**
     * 响应码
     */
    private Integer result;
    /**
     * 公共交易信息
     */
    private TraceInfoBean traceInfoBean;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 分页对象
     */
    private PageInfoVO pageInfoVO;
    /**
     * 错误信息
     */
    private ErrorInfoVO errorInfoVO;

    public ResponseVO() {
        super();
    }

    public ResponseVO(ResultEnum resultEnum){
        this.errorInfoVO = new ErrorInfoVO(resultEnum.getCode(),resultEnum.getMessage());
        this.data = null;
    }

    public ResponseVO(Integer code, String errMsg) {
        this.errorInfoVO = new ErrorInfoVO(code,errMsg);
        this.data = null;
    }

    /**
     * 构建成功请求结果
     * @param data 响应数据
     * @param <T> 响应数据类型
     * @return responseResult
     */
    public static <T> ResponseVO buildSuccess(T data) {
        ResponseVO responseResult = new ResponseVO();
        responseResult.setData(data);
        responseResult.setResult(ResultEnum.SUCCESS.getCode());
        return responseResult;
    }
}