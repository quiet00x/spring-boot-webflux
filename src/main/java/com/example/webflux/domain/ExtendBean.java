package com.example.webflux.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/7/6 - 23:37
 * @author: Mr_Bangb
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category_orgnation")
public class ExtendBean {
    // 模拟扩展属性作为原属性
    private String category;
    private String typeId;
    private String newAttr1;
    // 扩展属性
    Map<String, Object> extendInfo;
}