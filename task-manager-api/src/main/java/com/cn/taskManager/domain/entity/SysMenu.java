package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_menu")
public class SysMenu extends SuperEntity<SysMenu> {
    @TableField("pid")
    private String pid;
    @TableField("title")
    private String title;
    @TableField("url")
    private String url;
    @TableField("icon")
    private String icon;
    @TableField("menu_type")
    private Byte menuType;
    @TableField("sort")
    private Byte sort;
    @TableField("status")
    private Byte status;
    @TableField("rule_id")
    private String ruleId;
    @TableField("module")
    private String module;
    @TableField("menu")
    private String menu;
    @TableField("rule_name")
    private String ruleName;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> child;
    /**
     * 是否选中
     */
    @TableField(exist = false)
    private Boolean selected = false;
    /**
     * 级别
     */
    @TableField(exist = false)
    private Integer level;
    /**
     * 全名
     */
    @TableField(exist = false)
    private String fullName;
    /**
     * 关键权限
     */
    @TableField(exist = false)
    private SysRule rule;

}
