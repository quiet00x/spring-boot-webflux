package com.example.webflux.common.exception;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.common.utils.CommonUtils;
import com.example.webflux.domain.TraceInfoBean;
import com.example.webflux.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:54
 * @author: Mr_Bangb
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 缺少请求体异常处理器
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> ResponseVO<T> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        TraceInfoBean traceInfo = CommonUtils.getTraceInfo();
        log.error("--------------------> traceInfo: {}",traceInfo, e);
        CommonUtils.cleanResource();

        return new ResponseVO(ResultEnum.PARAMETER_ERROR.getCode(),e.getMessage());
    }

    /**
     * 参数效验异常处理器
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ResponseVO<T> parameterExceptionHandler(MethodArgumentNotValidException e) {
        TraceInfoBean traceInfo = CommonUtils.getTraceInfo();
        log.error("--------------------> traceInfo: {}",traceInfo, e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);

                ResponseVO responseVO = new ResponseVO(ResultEnum.PARAMETER_ERROR.getCode()
                        , fieldError.getDefaultMessage());
                responseVO.setTraceInfoBean(traceInfo);
                CommonUtils.cleanResource();
                return responseVO;
            }
        }
        ResponseVO responseVO = new ResponseVO(ResultEnum.PARAMETER_ERROR);
        responseVO.setTraceInfoBean(traceInfo);
        CommonUtils.cleanResource();
        return new ResponseVO(ResultEnum.PARAMETER_ERROR);
    }

    /**
     * 自定义异常处理器
     * @param e 自定义异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LocalException.class)
    public <T> ResponseVO<T> parameterExceptionHandler(LocalException e) {
        TraceInfoBean traceInfo = CommonUtils.getTraceInfo();
        log.error("--------------------> traceInfo: {}",traceInfo, e);

        ResponseVO responseVO = new ResponseVO(ResultEnum.PARAMETER_ERROR);
        responseVO.setTraceInfoBean(traceInfo);
        // 清空线程本地变量
        CommonUtils.cleanResource();

        return responseVO;
    }

}