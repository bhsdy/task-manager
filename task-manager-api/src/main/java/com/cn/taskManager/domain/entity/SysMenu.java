package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class SysMenu extends BaseEntity {
    private String id;

    private String pid;

    private String title;

    private String url;

    private String icon;

    private Byte menuType;

    private Byte sort;

    private Byte status;

    private String ruleId;

    private String module;

    private String menu;

    private String ruleName;
    /**
     * 子菜单
     */
    @Transient
    private List<SysMenu> child;
    /**
     * 是否选中
     */
    @Transient
    private Boolean selected = false;
    /**
     * 级别
     */
    @Transient
    private Integer level;
    /**
     * 全名
     */
    @Transient
    private String fullName;
    /**
     * 关键权限
     */
    @Transient
    private SysRule rule;

}
