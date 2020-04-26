package com.czbank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-04-21 11:55:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 769744065415731630L;
    
    private Long id;

    @NotBlank(message = "名字不能为空！")
    private String name;

    private BigDecimal score;

    @Email(message = "邮箱不符合规则！")
    private String email;

    /**
     * 收获地址地址
     */
    private List<Adress> adressList;
    /**
     * 分数之和
     */
    private BigDecimal scoreSum;

}