package com.cn.taskManager.domain.pojo;

public class SysStructure {
    private String id;

    private String name;

    private String pid;

    private Byte status;

    public SysStructure(String id, String name, String pid, Byte status) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.status = status;
    }

    public SysStructure() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}