package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "sys_user_detail")
public class SysUserDetail extends BaseEntity {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "photo")
    private String photo;

}
