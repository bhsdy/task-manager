package com.cn.taskManager.common;

import com.cn.taskManager.common.constants.Constant;
import com.cn.taskManager.common.utils.EncryptUtil;
import com.cn.taskManager.domain.entity.SysUser;
import com.cn.taskManager.domain.service.backend.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共控制器
 * @author leo
 *
 */
public class CommonController {
	@Autowired
	private SysUserService sysUserService;


	/**
	 * 获取当前登录用户
	 * @return
	 */
	public SysUser getCurrentUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String authKey = request.getHeader(Constant.AUTH_KEY);
		if(StringUtils.isNotBlank(authKey)) {
			String decryptAuthKey = EncryptUtil.decryptBase64(authKey, Constant.SECRET_KEY);
			String[]  auths = decryptAuthKey.split("\\|");
			String username = auths[0];
			String password = auths[1];
			SysUser record = new SysUser();
			record.setUserName(username);
			record.setPassword(password);
			return sysUserService.selectOne(record);
		}
		return null;
	}
}
