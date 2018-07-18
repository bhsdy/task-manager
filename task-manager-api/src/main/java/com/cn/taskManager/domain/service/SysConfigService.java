package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class SysConfigService extends BaseServiceImpl<SysConfig> {
	@Autowired
	private SysConfigMapper sysConfigMapper;

	@Override
	public Mapper<SysConfig> getMapper() {
		return sysConfigMapper;
	}

}
