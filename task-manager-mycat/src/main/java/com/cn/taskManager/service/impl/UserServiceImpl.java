package com.cn.taskManager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cn.taskManager.mapper.UserMapper;
import com.cn.taskManager.model.User;
import com.cn.taskManager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public void add(User u) {
		userMapper.add(u);
	}

	@Override
	public List<User> find() {
		return userMapper.find();
	}

	
	
	
	
}
