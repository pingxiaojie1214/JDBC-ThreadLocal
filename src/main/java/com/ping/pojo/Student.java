package com.ping.pojo;

import java.io.Serializable;

/**
 * 学生实体类
 * @author pxj
 * 2020-03-01
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 8162871076163670864L;
    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private  String name;
    /**
     * 班级
     */
    private String clazzId;
    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
