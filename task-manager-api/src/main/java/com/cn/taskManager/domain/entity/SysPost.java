package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import java.util.Date;
@Data
public class SysPost extends BaseEntity {
    private String id;

    private String name;

    private String remark;

    private Date createTime;

    private Byte status;

    private String structureId;
}
