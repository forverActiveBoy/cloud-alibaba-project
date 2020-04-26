package com.czbank.service;

import com.alibaba.fastjson.JSON;
import com.czbank.ProviderApplicatiion;
import com.czbank.dao.StudentDao;
import com.czbank.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;


/**
 * @author foreverActiveBoy
 * @date 2020/4/26 19:31
 * @apiNote
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = {ProviderApplicatiion.class})
@Slf4j
public class StudentServiceTest {
    @Resource
    private StudentDao studentDao;

    /**
     * 根据条件查询
     */
    @Test
    public void queryList() {
        Student student = Student.builder()
                .name("曹操")
                .build();
        List<Student> studentList = studentDao.queryAll(student);
        studentList.forEach((s)->{
            log.info("学生对象为:[{}]",s);
        });
    }

    /**
     * 查询学生对象总分数
     */
    @Test
    public void selectScoreSum(){
        Student student = studentDao.selectScoreSumByName("曹操");
        log.info("学生对象:[{}]",JSON.toJSONString(student));
    }

    /**
     * 一对多关联查询
     */
    @Test
    public void queryStudentAdress(){
        List<Student> studentList = studentDao.selectAdressList("曹操");
        studentList.forEach((s)->{
            log.info("学生对象:[{}]", JSON.toJSONString(s));
        });
    }

    @Test
    public void addStudent() {
    }
}
