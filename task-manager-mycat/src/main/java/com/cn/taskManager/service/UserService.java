package com.cn.taskManager.service;

import java.util.List;

import com.cn.taskManager.model.User;

public interface UserService {

	public void add(User u);
	
	List<User> find();
	
}
