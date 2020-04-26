package com.example.webflux.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.webflux.validation.constraints.ValidCardNumber;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/2 - 18:44
 * @author: Mr_Bangb
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("my_user")
public class UserBean {

    @NotBlank(message = "编号不能为空")
    @Max(value = 1000,message = "id不能大于1000")
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ValidCardNumber
    @TableField(exist = false)
    private String cardNumber;

    @NotBlank(message = "年龄不能为空！")
    private String age;

    private Date birthday;

    private String sex;

    private BigDecimal salary;

    @TableField(exist = false)
    private String tableName;

    @TableField(exist = false)
    private String transCode;
}