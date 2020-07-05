package com.example.webflux.vo;

import com.example.webflux.common.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/15 - 0:36
 * @author: Mr_Bangb
 * 错误信息
 */
@Data
@Builder
public class ErrorInfoVO {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String errDetail;

    /**
     * 通过枚举构建返回错误信息
     * @param resultEnum 枚举
     * @return ErrorInfoVO
     */
    public static <T> ErrorInfoVO buildErrorInfoVo(ResultEnum resultEnum) {
        ErrorInfoVO errorInfoVO = ErrorInfoVO.builder()
                .code(resultEnum.getCode())
                .errDetail(resultEnum.getMessage()).build();
        return errorInfoVO;
    }

    /**
     * 通过枚举构建返回错误信息
     * @param curCode 当前错误码
     * @param curErrMsg 错误信息
     * @return ErrorInfoVO
     */
    public static <T> ErrorInfoVO buildErrorInfoVo(String curCode, String curErrMsg) {
        ErrorInfoVO errorInfoVO = ErrorInfoVO.builder()
                .code(curCode)
                .errDetail(curErrMsg).build();
        return errorInfoVO;
    }
}