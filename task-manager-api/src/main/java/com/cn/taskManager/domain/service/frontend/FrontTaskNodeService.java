package com.cn.taskManager.domain.service.frontend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskMapper;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskNodeMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class FrontTaskNodeService extends BaseServiceImpl<FrontTaskNode> {
	@Autowired
	private FrontTaskNodeMapper frontTaskNodeMapper;

	@Override
	public Mapper<FrontTaskNode> getMapper() {
		return frontTaskNodeMapper;
	}
	/**
	 * 查询任务列表
	 */
	public List<FrontTaskNode> queryHeightTaskList(FrontTask record){
		List<FrontTaskNode> frontTaskNodes = frontTaskNodeMapper.queryHeightTaskList();
		return frontTaskNodes;
	}
}
