package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysGroup;
import com.cn.taskManager.domain.mapper.SysGroupMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class SysGroupService extends BaseServiceImpl<SysGroup> {
	@Autowired
	private SysGroupMapper sysGroupMapper;

	@Override
	public Mapper<SysGroup> getMapper() {
		return sysGroupMapper;
	}
	/**
	 * 列表
	 * @return
	 */
	public List<Map<String, Object>> getDataList() {
		Example example = new Example(SysGroup.class);
		List<SysGroup> rootSysGroups = sysGroupMapper.selectByExample(example);
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
