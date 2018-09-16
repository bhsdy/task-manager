package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

import java.util.List;

@Data
@TableName("front_task")
public class FrontTask extends SuperEntity<FrontTask> {
    @TableField("task_name")
    private String taskName;
    @TableField("source")
    private String source;
    @TableField("accomplish")
    private String accomplish;
    @TableField("dict_code")
    private String dictCode;
    @TableField("task_detail")
    private String taskDetail;
    @TableField("input")
    private String input;
    @TableField("output")
    private String output;
    @TableField("progress")
    private String progress;
    @TableField("remark")
    private String remark;
    @TableField(exist = false)
    private List<FrontTaskNode> frontTaskNodeList;
    @TableField(exist = false)
    private SysDict sysDict;
}
