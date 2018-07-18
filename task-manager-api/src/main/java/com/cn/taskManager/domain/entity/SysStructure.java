package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

@Data
public class SysStructure extends BaseEntity {
    private String id;

    private String name;

    private String pid;

    private Byte status;

}
