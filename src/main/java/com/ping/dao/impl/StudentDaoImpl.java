package com.ping.dao.impl;

import com.ping.dao.StudentDao;
import com.ping.pojo.Clazz;
import com.ping.pojo.Student;
import com.ping.utils.JDBCBase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class StudentDaoImpl implements StudentDao {
    //为了事务起作用，异常抛到service层处理。
    @Override
    public int addStudent(Student student){
        String sql = "insert into t_student(id,name,clazzId,createTime) values(?,?,?,?)";
        int rows = JDBCBase.executeSQL(sql,student.getId(),student.getName(),student.getClazzId(),student.getCreateTime());
        return rows;
    }

    @Override
    public int addClazz(Clazz clazz){
        String sql = "insert into t_clazz(id,name,createTime) values(?,?,?)";
        int rows = JDBCBase.executeSQL(sql,clazz.getId(),clazz.getName(),clazz.getCreateTime());
        return rows;
    }

    @Override
    public Clazz getClazzByName(String name){
        String sql = "select * from t_clazz where name = ?";
        String mapColumn = "id,createTime,createTime";
        List<Map<String, Object>> list = JDBCBase.querySQL(sql,mapColumn,name);
        if(list.size()>0){
            Clazz clazz = new Clazz();
            clazz.setId(list.get(0).get("id").toString());
            clazz.setName(list.get(0).get("name").toString());
            clazz.setCreateTime(list.get(0).get("createTime").toString());
            System.out.println(clazz.toString());
            return clazz;
        }
        return null;
    }
}
