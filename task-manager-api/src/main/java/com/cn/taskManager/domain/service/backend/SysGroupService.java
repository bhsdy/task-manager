package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysGroup;

import java.util.List;
import java.util.Map;

public interface SysGroupService extends IService<SysGroup> {
	/**
	 * 列表
	 * @return
	 */
	List<Map<String, Object>> getDataList();

}
