package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "sys_rule")
public class SysRule extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "rule_name")
    private String ruleName;
    @Column(name = "level")
    private Byte level;
    @Column(name = "pid")
    private String pid;
    @Column(name = "status")
    private Byte status;
    @Transient
    private List<SysRule> child;
}
