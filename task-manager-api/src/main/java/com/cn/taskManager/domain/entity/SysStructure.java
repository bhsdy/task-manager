package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

@Data
@TableName("sys_structure")
public class SysStructure extends SuperEntity<SysStructure> {
    @TableField("name")
    private String name;
    @TableField("pid")
    private String pid;
    @TableField("status")
    private Byte status;

}
