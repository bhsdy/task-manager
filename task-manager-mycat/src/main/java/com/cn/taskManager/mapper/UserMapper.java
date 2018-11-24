package com.cn.taskManager.mapper;

import java.util.List;

import com.cn.taskManager.model.User;

public interface UserMapper {

	
	void add(User u);
	
	List<User> find();
	
}
