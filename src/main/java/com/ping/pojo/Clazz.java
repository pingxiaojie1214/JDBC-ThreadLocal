package com.ping.pojo;

import java.io.Serializable;

/**
 * @author pxj
 */
public class Clazz implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 名字
     */
    private  String name;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
