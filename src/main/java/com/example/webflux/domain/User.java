package com.example.webflux.domain;

import com.example.webflux.validation.constraints.ValidCardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:44
 * @author: Mr_Bangb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "编号不能为空")
    @Max(value = 1000,message = "id不能大于1000")
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @ValidCardNumber
    private String cardNumber;
}