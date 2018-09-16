package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysStructure;

import java.util.List;
import java.util.Map;

public interface SysStructureService extends IService<SysStructure> {

	List<Map<String, Object>> getDataList();

}
