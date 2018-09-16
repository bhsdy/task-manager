package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

import java.util.Date;
@Data
@TableName("sys_post")
public class SysPost extends SuperEntity<SysPost> {
    @TableField("name")
    private String name;
    @TableField("remark")
    private String remark;
    @TableField("create_time")
    private Date createTime;
    @TableField("status")
    private Byte status;
    @TableField("structure_id")
    private String structureId;
}
