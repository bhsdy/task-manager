package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_rule")
public class SysRule extends SuperEntity<SysRule> {
    @TableField("title")
    private String title;
    @TableField("rule_name")
    private String ruleName;
    @TableField("level")
    private Byte level;
    @TableField("pid")
    private String pid;
    @TableField("status")
    private Byte status;
    @TableField(exist = false)
    private List<SysRule> child;
}
