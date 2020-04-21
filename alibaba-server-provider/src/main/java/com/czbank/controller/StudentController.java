package com.czbank.controller;

import com.czbank.entity.Student;
import com.czbank.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author foreverActiveBoy
 */

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = {"/v1/student"},method = {RequestMethod.GET})
    public List<Student> queryList(){
        return studentService.queryList();
    }
}
