package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
	/**
	 * 修改密码
	 * @param currentUser 当前登录的用户信息
	 * @param old_pwd
	 * @param new_pwd
	 * @return 修改失败返回错误信息，修改成功返回authKey信息。
	 */
	String setInfo(SysUser currentUser, String old_pwd, String new_pwd)throws Exception;

	Page<SysUser> getDataList(SysUser record)throws Exception;

	SysUser save(SysUser sysUser)throws Exception;

	Boolean delete(String id)throws Exception;

	Boolean deleteBatch(List<String> ids)throws Exception;
}
