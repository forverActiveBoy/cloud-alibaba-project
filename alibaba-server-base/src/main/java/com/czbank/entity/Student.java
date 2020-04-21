package com.czbank.entity;

import lombok.Builder;
import lombok.Data;

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
    
    private String name;
    
    private BigDecimal score;
    
    private String email;

}