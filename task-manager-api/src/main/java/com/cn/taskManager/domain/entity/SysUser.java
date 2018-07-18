package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import java.util.Date;
@Data
public class SysUser extends BaseEntity {
    private String id;

    private String userName;

    private String password;

    private String remark;

    private Date createTime;

    private String realName;

    private String structureId;

    private String postId;

    private Byte status;

}
