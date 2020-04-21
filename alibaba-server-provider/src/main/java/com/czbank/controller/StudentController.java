package com.czbank.controller;

import com.czbank.entity.Student;
import com.czbank.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author foreverActiveBoy
 * 备注:使用rest风格
 */
@RestController
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = {"/v1/student"},method = {RequestMethod.GET})
    public List<Student> queryList(){
        return studentService.queryList();
    }

    @RequestMapping(value = {"/v1/student"},method = {RequestMethod.POST})
    public void addOne(@Valid @RequestBody Student student){
        log.info("添加学生对象入参student:[{}]",student);
        studentService.addStudent(student);
    }
}
