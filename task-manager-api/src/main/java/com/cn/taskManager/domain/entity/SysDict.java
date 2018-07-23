package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Table(name = "sys_dict")
public class SysDict extends BaseEntity {
    @Column(name = "dict_code")
    private String dictCode;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "dict_group_code")
    private String dictGroupCode;

}
