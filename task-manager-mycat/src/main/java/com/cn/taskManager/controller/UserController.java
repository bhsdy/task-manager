package com.cn.taskManager.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.cn.taskManager.feign.TaskManagerApiFeign;
import com.cn.taskManager.model.User;
import com.cn.taskManager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserController", description = "User接口")
public class UserController {

	@Resource
	private UserService userService;
	@Autowired
	private TaskManagerApiFeign taskManagerApiFeign;

	@ApiOperation(value = "查询别的数据库的用户", httpMethod="GET")
	@GetMapping("/queryUser")
	public String queryUser(){
		return taskManagerApiFeign.queryUser();
	}

	@ApiOperation(value = "新增", httpMethod="POST")
	@RequestMapping("/user/add")
	public String add(String name) {
		User u = new User();
		u.setName(name).setIndate(new Date());
		userService.add(u);
		return "插入成功";
	}

	@ApiOperation(value = "查找", httpMethod="GET")
	@RequestMapping("/user/find")
	public List<User> find() {
		return userService.find();
	}

}

