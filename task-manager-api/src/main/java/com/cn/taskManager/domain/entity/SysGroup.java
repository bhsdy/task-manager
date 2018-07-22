package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "sys_group")
public class SysGroup extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "rules")
    private String rules;
    @Column(name = "pid")
    private String pid;
    @Column(name = "remark")
    private String remark;
    @Column(name = "status")
    private Byte status;


}
