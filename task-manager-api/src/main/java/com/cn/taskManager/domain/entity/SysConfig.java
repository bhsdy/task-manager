package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

@Data
public class SysConfig extends BaseEntity {
    private String id;

    private String name;

    private String value;

    private Byte group;

    private Byte needAuth;

}
