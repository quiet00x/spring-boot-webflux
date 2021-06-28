package com.example.webflux.common.constant;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/22 - 0:26
 * @author: Mr_Bangb
 */
public interface MagicConstant {
    /**
     * 全量初始化
     */
    String IMPORT_MODEL_INIT = "0";

    /**
     * 增量覆盖
     */
    String IMPORT_MODEL_INCREMENT = "1";

    /**
     * 纯增量
     */
    String IMPORT_MODEL_ONLY_INCREMENT = "2";
}