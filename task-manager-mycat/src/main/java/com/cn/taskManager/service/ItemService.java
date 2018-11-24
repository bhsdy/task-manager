package com.cn.taskManager.service;

import com.cn.taskManager.model.Item;

import java.util.List;

public interface ItemService {
	
	public void add(Item i);
	
	List<Item> find();
	
}
