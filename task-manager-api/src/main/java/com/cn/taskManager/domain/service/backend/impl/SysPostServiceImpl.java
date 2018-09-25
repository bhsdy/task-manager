package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.SysPost;
import com.cn.taskManager.domain.mapper.backend.SysPostMapper;
import com.cn.taskManager.domain.service.backend.SysPostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

	@Autowired
	private SysPostMapper sysPostMapper;

	public List<SysPost> getDataList(String name) {
		/*Example example = new Example(SysPost.class,false);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andLike("name", name);
		}
		return sysPostMapper.selectByExample(example);*/
		EntityWrapper<SysPost> ew = new EntityWrapper<>();
		if (!StringUtils.isEmpty(name)) {
			ew.like("name", name);
		}
		return this.selectList(ew);

	}

}
