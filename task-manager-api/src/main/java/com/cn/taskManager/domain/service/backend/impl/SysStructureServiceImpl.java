package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysStructure;
import com.cn.taskManager.domain.mapper.backend.SysStructureMapper;
import com.cn.taskManager.domain.service.backend.SysStructureService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysStructureServiceImpl extends ServiceImpl<SysStructureMapper, SysStructure> implements SysStructureService {

	@Autowired
	private SysStructureMapper sysStructureMapper;


	public List<Map<String, Object>> getDataList() {
		/*Example example = new Example(SysStructure.class);
		example.setOrderByClause(" id asc");
		List<SysStructure> rootSysStructure = sysStructureMapper.selectByExample(example);*/
		EntityWrapper<SysStructure> ew = new EntityWrapper<>();
		ew.orderBy("id",true);
		List<SysStructure> rootSysStructure = this.selectList(ew);

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
