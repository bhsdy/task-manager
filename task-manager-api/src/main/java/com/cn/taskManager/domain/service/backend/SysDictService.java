package com.cn.taskManager.domain.service.backend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.SysDict;
import com.cn.taskManager.domain.mapper.backend.SysDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public class SysDictService extends BaseServiceImpl<SysDict> {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public Mapper<SysDict> getMapper() {
		return sysDictMapper;
	}

}
