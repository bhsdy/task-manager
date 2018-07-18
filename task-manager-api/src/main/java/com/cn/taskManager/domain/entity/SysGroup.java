package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

@Data
public class SysGroup extends BaseEntity {
    private String id;

    private String title;

    private String rules;

    private String pid;

    private String remark;

    private Byte status;


}
