package com.cn.taskManager.model;

import java.util.Date;

public class Item {
    private Integer id;

    private Integer value;

    private Date indate;

    public Item(Integer id, Integer value, Date indate) {
        this.id = id;
        this.value = value;
        this.indate = indate;
    }

    public Item() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
}