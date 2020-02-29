package com.ping.controller;

import com.ping.pojo.Clazz;
import com.ping.pojo.Student;
import com.ping.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 学生信息控制层
 * @author pxj
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/test")
    public void test(){
        //创建时间格式转换
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sdf.format(date);
        //创建班级实体类
        Clazz clazz = new Clazz();
        String cid = "Java_001";
        clazz.setId(cid);
        clazz.setName("JAVA106");
        clazz.setCreateTime(d);
        //创建学生实体类
        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setName("张三");
        student.setClazzId(cid);
        student.setCreateTime(d);
        boolean flag = studentService.addStuAndClazz(student,clazz);
        if(flag){
            System.out.println("=================添加成功！=================");
        }else{
            System.out.println("=================添加失败！=================");
        }
    }
}
