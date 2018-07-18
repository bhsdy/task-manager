package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class SysRule extends BaseEntity {
    private String id;

    private String title;

    private String ruleName;

    private Byte level;

    private String pid;

    private Byte status;
    @Transient
    private List<SysRule> child;
}
