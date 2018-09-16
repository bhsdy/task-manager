package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

import java.util.Date;
@Data
@TableName("front_task_node")
public class FrontTaskNode extends SuperEntity<FrontTaskNode> {
    @TableField("task_id")
    private String taskId;
    @TableField("time")
    private Date time;
    @TableField("log")
    private String log;
    @TableField("output")
    private String output;

}
