package com.cn.taskManager.domain.service.frontend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.mapper.backend.SysConfigMapper;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class FrontTaskService extends BaseServiceImpl<FrontTask> {
	@Autowired
	private FrontTaskMapper frontTaskMapper;

	@Override
	public Mapper<FrontTask> getMapper() {
		return frontTaskMapper;
	}

}
