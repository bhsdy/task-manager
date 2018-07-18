package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.domain.entity.SysPost;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Service
public class SysPostService extends BaseServiceImpl<SysPost> {

	@Autowired
	private Mapper<SysPost> sysPostMapper;

	@Override
	public Mapper<SysPost> getMapper() {
		return sysPostMapper;
	}

	public List<SysPost> getDataList(String name) {
		Example example = new Example(SysPost.class,false);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andLike("name", name);
		}
		return sysPostMapper.selectByExample(example);
	}

}
