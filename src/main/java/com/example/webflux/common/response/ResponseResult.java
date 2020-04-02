package com.example.webflux.common.response;

import com.example.webflux.common.enums.ResultEnum;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/3 - 0:57
 * @author: Mr_Bangb
 */
@Data
public class ResponseResult {
    private Integer code;
    private String msg;

    public ResponseResult() {
        super();
    }

    public ResponseResult(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}