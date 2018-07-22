package com.cn.taskManager.domain.entity;

import com.cn.taskManager.common.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "remark")
    private String remark;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "structure_id")
    private String structureId;
    @Column(name = "post_id")
    private String postId;
    @Column(name = "status")
    private Byte status;
    @Transient
    private SysUserDetail sysUserDetail;
    @Transient
    private SysPost sysPost;
    @Transient
    private SysStructure sysStructure;

}
