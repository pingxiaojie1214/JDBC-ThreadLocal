package com.ping.service;

import com.ping.pojo.Clazz;
import com.ping.pojo.Student;

/**
 * @author pxj
 */
public interface StudentService {
    /**
     * 新增学生与班级信息
     * */
    boolean addStuAndClazz(Student student, Clazz clazz);
}
