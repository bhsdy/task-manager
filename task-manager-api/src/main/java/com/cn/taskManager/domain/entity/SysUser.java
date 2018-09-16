package com.cn.taskManager.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cn.taskManager.common.mybatisplus.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
@ApiModel(value="user对象",description="用户对象user")
public class SysUser extends SuperEntity<SysUser> {
    @TableField("user_name")
    @ApiModelProperty(value="用户名")
    private String userName;

    @ApiModelProperty(value="用户密码")
    @TableField("password")
    private String password;

    @TableField("remark")
    @ApiModelProperty(value="备注")
    private String remark;

    @TableField("create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @TableField("real_name")
    @ApiModelProperty(value="真实名字")
    private String realName;

    @TableField("structure_id")
    @ApiModelProperty(value="所属部门ID")
    private String structureId;

    @TableField("post_id")
    @ApiModelProperty(value="所属岗位ID")
    private String postId;

    @TableField("status")
    @ApiModelProperty(value="启用状态")
    private Byte status;

    @TableField(exist = false)
    private SysUserDetail sysUserDetail;
    @TableField(exist = false)
    private SysPost sysPost;
    @TableField(exist = false)
    private SysStructure sysStructure;

}
