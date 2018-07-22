package com.cn.taskManager.domain.service.frontend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskMapper;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public class FrontTaskService extends BaseServiceImpl<FrontTask> {
	@Autowired
	private FrontTaskMapper frontTaskMapper;
	@Autowired
	private FrontTaskNodeService frontTaskNodeService;
	@Override
	public Mapper<FrontTask> getMapper() {
		return frontTaskMapper;
	}

	/**
	 * 重写sava 实现,同时保存字表
	 * @param record 对象
	 * @return FrontTask
	 */
	@Override
	public FrontTask save(FrontTask record) {
		FrontTask frontTask = super.save(record);
		if(frontTask != null){
			List<FrontTaskNode> frontTaskNodes = frontTaskNodeService.batchSave(frontTask.getFrontTaskNodeList());
			frontTask.setFrontTaskNodeList(frontTaskNodes);
		}
		return frontTask;
	}
}
