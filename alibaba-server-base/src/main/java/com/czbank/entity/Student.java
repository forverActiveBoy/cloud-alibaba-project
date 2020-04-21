package com.czbank.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-04-21 11:55:47
 */
@Data
@Builder
public class Student implements Serializable {
    private static final long serialVersionUID = 769744065415731630L;
    
    private Long id;

    @NotBlank(message = "名字不能为空！")
    private String name;

    private BigDecimal score;

    @Email(message = "邮箱不符合规则！")
    private String email;

}