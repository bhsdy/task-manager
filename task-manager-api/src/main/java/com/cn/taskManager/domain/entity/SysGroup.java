package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

@Data
@TableName("sys_group")
public class SysGroup extends SuperEntity<SysGroup> {
    @TableField("title")
    private String title;
    @TableField("rules")
    private String rules;
    @TableField("pid")
    private String pid;
    @TableField("remark")
    private String remark;
    @TableField("status")
    private Byte status;


}
