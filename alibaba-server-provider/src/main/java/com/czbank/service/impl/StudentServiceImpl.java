package com.czbank.service.impl;

import com.czbank.dao.StudentDao;
import com.czbank.entity.Student;
import com.czbank.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author foreverActiveBoy
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(readOnly = true)
    public List<Student> queryList() {
        Student student = new Student();
            student.setName("曹操");
        return studentDao.queryAll(student);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public void addStudent(Student student) {
        studentDao.insert(student);
    }
}
