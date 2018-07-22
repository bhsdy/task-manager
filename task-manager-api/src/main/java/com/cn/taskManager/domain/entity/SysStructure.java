package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "sys_structure")
public class SysStructure extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "pid")
    private String pid;
    @Column(name = "status")
    private Byte status;

}
