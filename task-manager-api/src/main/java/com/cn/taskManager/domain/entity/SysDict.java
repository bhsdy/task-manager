package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;


@Data
@TableName("sys_dict")
public class SysDict extends SuperEntity<SysDict> {
    @TableField("dict_code")
    private String dictCode;
    @TableField("name")
    private String name;
    @TableField("status")
    private String status;
    @TableField("dict_group_code")
    private String dictGroupCode;

}
