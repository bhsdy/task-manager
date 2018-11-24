package com.cn.taskManager.controller;

import java.util.Date;
import java.util.List;

import com.cn.taskManager.model.Item;
import com.cn.taskManager.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ItemController", description = "Item接口")
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@ApiOperation(value = "新增", httpMethod="POST")
	@RequestMapping("/add")
	public String add(int id,int value) {
		Item i = new Item();
		i.setId(id);
		i.setValue(value);
		i.setIndate(new Date());
		itemService.add(i);
		return "添加成功";
	}


	@ApiOperation(value = "查找", httpMethod="GET")
	@RequestMapping("/find")
	public List<Item> find() {
		return itemService.find();
	}
	
	
	
}
