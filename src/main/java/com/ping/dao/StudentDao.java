package com.ping.dao;

import com.ping.pojo.Clazz;
import com.ping.pojo.Student;

public interface StudentDao {
    /**
     * 为了方便，student和clazz写一起了
     */

    /**
     * 新增学生。
     * @param student
     * @return
     */
    int addStudent(Student student);

    /**
     * 新增班级。
     * @param clazz
     * @return
     */
    int addClazz(Clazz clazz);

    /**
     * 通过id获取班级信息。
     * @param name
     * @return
     */
    Clazz getClazzByName(String name);
}
