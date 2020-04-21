package com.czbank.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * (Adress)实体类
 *
 * @author makejava
 * @since 2020-04-21 11:35:57
 */
@Data
@Builder
public class Adress implements Serializable {
    private static final long serialVersionUID = 642266021422802826L;
    
    private Long id;
    
    private String adress;
    
    private Long studentId;

}