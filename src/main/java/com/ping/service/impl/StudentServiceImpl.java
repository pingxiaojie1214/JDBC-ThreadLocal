package com.ping.service.impl;

import com.ping.dao.StudentDao;
import com.ping.pojo.Clazz;
import com.ping.pojo.Student;
import com.ping.service.StudentService;
import com.ping.utils.JDBCBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pxj
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * @param student
     * @param clazz
     * @return
     */
    @Override
    public boolean addStuAndClazz(Student student, Clazz clazz) {
        //假设逻辑为插入学生与班级，若是班级已经存在，则只插入学生。
        //开启事务
        JDBCBase.startTransaction();
        boolean flag = false;
        int row2 = 1;
        try {
            int row1 = studentDao.addStudent(student);
            Clazz c = studentDao.getClazzByName("JAVA106");
            if(c == null){
                row2 = studentDao.addClazz(clazz);
            }
            if(row1 == 1 && row2 == 1){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JDBCBase.rollback();
        }
        JDBCBase.commit();
        return flag;
    }
}
