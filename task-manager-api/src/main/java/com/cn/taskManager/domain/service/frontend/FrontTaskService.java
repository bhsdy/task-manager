package com.cn.taskManager.domain.service.frontend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.entity.SysDict;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskMapper;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskNodeMapper;
import com.cn.taskManager.domain.service.backend.SysDictService;
import com.github.pagehelper.PageInfo;
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
	@Autowired
	private SysDictService sysDictService;
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

	/**
	 * 查询任务列表
	 */
	public PageInfo<FrontTask> queryTaskList(FrontTask record){
		PageInfo<FrontTask> frontTaskPageInfo = super.selectPage(record.getRows(), record.getPage(), record);
		List<FrontTask> frontTaskList = frontTaskPageInfo.getList();
		frontTaskList.forEach(item ->{
			SysDict sysDict = new SysDict();
			sysDict.setDictCode(item.getDictCode());
			SysDict sysDictQuery = sysDictService.selectOne(sysDict);
			item.setSysDict(sysDictQuery);
		});
		return frontTaskPageInfo;
	}
}
