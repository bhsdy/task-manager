package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "sys_post")
public class SysPost extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "remark")
    private String remark;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "status")
    private Byte status;
    @Column(name = "structure_id")
    private String structureId;
}
