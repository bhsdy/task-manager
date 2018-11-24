package com.cn.taskManager.service.impl;

import java.util.List;

import com.cn.taskManager.mapper.ItemMapper;
import com.cn.taskManager.model.Item;
import com.cn.taskManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;
	
	@Override
	public void add(Item i) {
		itemMapper.insertSelective(i);
	}

	@Override
	public List<Item> find() {
//		return itemMapper.selectAll();
		return null;
	}

	
	
}
