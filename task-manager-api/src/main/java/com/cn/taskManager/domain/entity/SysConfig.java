package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name="sys_config")
public class SysConfig extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @Column(name = "group")
    private Byte group;
    @Column(name = "need_auth")
    private Byte needAuth;

}
