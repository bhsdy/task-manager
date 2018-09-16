package com.cn.taskManager.domain.service.frontend;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.FrontTask;

public interface FrontTaskService extends IService<FrontTask> {
	/**
	 * 新增任务
	 * @param record 对象
	 * @return FrontTask
	 */
	FrontTask addTask(FrontTask record);
	/**
	 * 编辑任务
	 * @param record 对象
	 * @return FrontTask
	 */
	FrontTask editTask(FrontTask record);

	/**
	 * 查询任务列表
	 */
	Page<FrontTask> queryTaskList(FrontTask record);
}
