package com.cn.taskManager.domain.service.backend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.SysUserDetail;
import com.cn.taskManager.domain.mapper.backend.SysUserDetailMapper;
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
