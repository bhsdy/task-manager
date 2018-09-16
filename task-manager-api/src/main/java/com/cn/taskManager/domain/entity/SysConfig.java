package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

@Data
@TableName("sys_config")
public class SysConfig extends SuperEntity<SysConfig> {
    @TableField("name")
    private String name;
    @TableField("value")
    private String value;
    @TableField("group")
    private Byte group;
    @TableField("need_auth")
    private Byte needAuth;

}
