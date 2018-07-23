package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "front_task")
public class FrontTask extends BaseEntity {
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "source")
    private String source;
    @Column(name = "accomplish")
    private String accomplish;
    @Column(name = "level")
    private String level;
    @Column(name = "task_detail")
    private String taskDetail;
    @Column(name = "input")
    private String input;
    @Column(name = "output")
    private String output;
    @Column(name = "progress")
    private String progress;
    @Column(name = "remark")
    private String remark;
    @Transient
    private List<FrontTaskNode> frontTaskNodeList;

}
