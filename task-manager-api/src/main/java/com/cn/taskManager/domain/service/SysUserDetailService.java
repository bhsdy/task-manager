package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.constants.Constant;
import com.cn.taskManager.common.utils.EncryptUtil;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysUserDetail;
import com.cn.taskManager.domain.entity.SysUserDetail;
import com.cn.taskManager.domain.mapper.SysUserDetailMapper;
import com.cn.taskManager.domain.mapper.SysUserDetailMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class SysUserDetailService extends BaseServiceImpl<SysUserDetail> {
	@Autowired
	private SysUserDetailMapper SysUserDetailMapper;

	@Override
	public Mapper<SysUserDetail> getMapper() {
		return SysUserDetailMapper;
	}
}
