package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.constants.Constant;
import com.cn.taskManager.common.utils.EncryptUtil;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysUser;
import com.cn.taskManager.domain.mapper.SysUserMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class SysUserService extends BaseServiceImpl<SysUser> {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public Mapper<SysUser> getMapper() {
		return sysUserMapper;
	}

	/**
	 * 修改密码
	 * @param currentUser 当前登录的用户信息
	 * @param old_pwd
	 * @param new_pwd
	 * @return 修改失败返回错误信息，修改成功返回authKey信息。
	 */
	public String setInfo(SysUser currentUser, String old_pwd, String new_pwd) {
		if (currentUser == null){
			return FastJsonUtils.resultError(-400, "请先登录", null);
		}

		if (StringUtils.isNotBlank(old_pwd)) {
			return FastJsonUtils.resultError(-400, "旧密码必填", null);
		}

		if(StringUtils.isNotBlank(new_pwd)) {
			return FastJsonUtils.resultError(-400, "新密码必填", null);
		}

		if (old_pwd.equals(new_pwd)) {
			return FastJsonUtils.resultError(-400, "新旧密码不能一样", null);
		}

		if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
			return FastJsonUtils.resultError(-400, "原密码错误", null);
		}

		if (!currentUser.getPassword().equals(DigestUtils.md5Hex(old_pwd))) {
			return FastJsonUtils.resultError(-400, "原密码错误", null);
		}
		SysUser record = new SysUser();
		record.setId(currentUser.getId());
		String md5NewPwd = DigestUtils.md5Hex(new_pwd);
		record.setPassword(md5NewPwd);
		sysUserMapper.updateByPrimaryKeySelective(record);
		String authKey = EncryptUtil.encryptBase64(currentUser.getUserName()+"|"+md5NewPwd, Constant.SECRET_KEY);
		//@TODO 更新缓存中auth_key
		return FastJsonUtils.resultError(200, "修改成功", authKey);
	}

	public PageInfo<SysUser> getDataList(SysUser record) {
		return super.selectPage(record.getPage(), record.getRows(), record);
	}

}
