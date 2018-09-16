package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;


@Data
@TableName("sys_user_detail")
public class SysUserDetail extends SuperEntity<SysUser> {
    @TableField("user_id")
    private String userId;
    @TableField("email")
    private String email;
    @TableField("phone")
    private String phone;
    @TableField("photo")
    private String photo;

}
