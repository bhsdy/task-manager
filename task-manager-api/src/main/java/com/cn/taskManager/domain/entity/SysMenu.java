package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "sys_menu")
public class SysMenu extends BaseEntity {
    @Column(name = "pid")
    private String pid;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "icon")
    private String icon;
    @Column(name = "menu_type")
    private Byte menuType;
    @Column(name = "sort")
    private Byte sort;
    @Column(name = "status")
    private Byte status;
    @Column(name = "rule_id")
    private String ruleId;
    @Column(name = "module")
    private String module;
    @Column(name = "menu")
    private String menu;
    @Column(name = "rule_name")
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
