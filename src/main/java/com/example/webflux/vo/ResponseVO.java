package com.example.webflux.vo;

import com.example.webflux.common.constant.ResponseConstant;
import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.domain.TraceInfoBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:57
 * @author: Mr_Bangb
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {
    /**
     * 响应码
     */
    private String result;
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

    /**
     * 构建成功请求结果
     * @param resultData 响应数据
     * @param <T> 响应数据类型
     * @return responseResult
     */
    public static <T> ResponseVO buildSuccess(T resultData) {

        return ResponseVO.builder()
                .data(resultData)
                .result(ResponseConstant.RSP_NORMAL_RESULT)
                .errorInfoVO(ErrorInfoVO.buildErrorInfoVo(ResultEnum.SUCCESS)).build();
    }

    /**
     * 构建成功请求结果
     * @param resultEnum 枚举
     * @param <T> 响应数据类型
     * @return responseResult
     */
    public static <T> ResponseVO buildSuccessWithoutData(ResultEnum resultEnum) {

        return ResponseVO.builder()
                .result(ResponseConstant.RSP_NORMAL_RESULT)
                .errorInfoVO(ErrorInfoVO.buildErrorInfoVo(resultEnum))
                .data(null).build();
    }

    /**
     * 构建成功请求结果
     * @param resultEnum 枚举
     * @param <T> 响应数据类型
     * @return responseResult
     */
    public static <T> ResponseVO buildErrorByResultEnum(ResultEnum resultEnum) {

        return ResponseVO.builder()
                .result(ResponseConstant.RSP_ERROR_RESULT)
                .errorInfoVO(ErrorInfoVO.buildErrorInfoVo(resultEnum))
                .data(null).build();
    }

    /**
     * 构建成功请求结果
     * @param errorCode 错误码
     * @param errorMsg 错误消息
     * @param <T> 响应数据类型
     * @return responseResult
     */
    public static <T> ResponseVO buildErrorByDetail(String errorCode, String errorMsg) {

        return ResponseVO.builder()
                .result(ResponseConstant.RSP_ERROR_RESULT)
                .errorInfoVO(ErrorInfoVO.buildErrorInfoVo(errorCode,errorMsg))
                .data(null).build();
    }
}