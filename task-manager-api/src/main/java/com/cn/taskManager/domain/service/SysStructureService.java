package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysStructure;
import com.cn.taskManager.domain.mapper.SysStructureMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class SysStructureService extends BaseServiceImpl<SysStructure> {

	@Autowired
	private SysStructureMapper sysStructureMapper;

	@Override
	public Mapper<SysStructure> getMapper() {
		return sysStructureMapper;
	}

	public List<Map<String, Object>> getDataList() {
		Example example = new Example(SysStructure.class);
		example.setOrderByClause(" id asc");
		List<SysStructure> rootSysStructure = sysStructureMapper.selectByExample(example);
		Map<String, String> fields = Maps.newHashMap();
		fields.put("cid", "id");
		fields.put("fid", "pid");
		fields.put("name", "name");
		fields.put("fullname", "title");
		List<Map<String, Object>> rawList = Lists.newArrayList();
		rootSysStructure.forEach((m)->{
			rawList.add(BeanToMapUtil.convertBean(m));
		});
		Category cate = new Category(fields, rawList);
		return cate.getRawList();
	}

}
