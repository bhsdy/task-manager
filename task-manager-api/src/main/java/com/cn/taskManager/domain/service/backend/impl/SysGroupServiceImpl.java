package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysGroup;
import com.cn.taskManager.domain.mapper.backend.SysGroupMapper;
import com.cn.taskManager.domain.service.backend.SysGroupService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements SysGroupService {
	/**
	 * 列表
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getDataList() {
		List<SysGroup> rootSysGroups = baseMapper.selectList(new EntityWrapper<>());

		Map<String, String> fields = Maps.newHashMap();
		fields.put("cid", "id");
		fields.put("fid", "pid");
		fields.put("name", "title");
		fields.put("fullname", "title");
		List<Map<String, Object>> rawList = Lists.newArrayList();
		rootSysGroups.forEach((m)->{
			rawList.add(BeanToMapUtil.convertBean(m));
		});
		Category cate = new Category(fields, rawList);
//		return cate.getList(Integer.valueOf("0"));
		return cate.getRawList();
	}

}
