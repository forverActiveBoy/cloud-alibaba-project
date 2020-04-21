package com.czbank.service;

import com.czbank.entity.Student;

import java.util.List;

/**
 * @author foreverActiveBoy
 */
public interface StudentService {
    /**
     * 查询所有
     * @return
     */
    List<Student> queryList();
}
