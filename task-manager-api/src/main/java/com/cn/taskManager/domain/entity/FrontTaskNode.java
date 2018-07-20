package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "front_task_node")
public class FrontTaskNode extends BaseEntity {
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "time")
    private Date time;
    @Column(name = "log")
    private String log;
    @Column(name = "output")
    private String output;

}
