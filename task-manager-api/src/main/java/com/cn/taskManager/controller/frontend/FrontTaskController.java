package com.cn.taskManager.controller.frontend;

import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.enums.ResultCode;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.mapper.backend.SysConfigMapper;
import com.cn.taskManager.domain.service.frontend.FrontTaskNodeService;
import com.cn.taskManager.domain.service.frontend.FrontTaskService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务接口
 * @author Johan
 */
@RestController
@RequestMapping("/api/frontTask")
@Api(value = "FrontTaskController", description = "任务接口")
public class FrontTaskController extends CommonController {
	@Autowired
	private FrontTaskService frontTaskService;
	@Autowired
	private FrontTaskNodeService frontTaskNodeService;

	@ApiOperation(value = "新增任务", httpMethod="POST")
	@PostMapping(value = "/save", produces = {"application/json;charset=UTF-8"})
	public String save(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		FrontTask frontTask = frontTaskService.save(record);
		if(frontTask == null) {
			return FastJsonUtils.toResponse(ResultCode.FAILED, null);
		}
		return FastJsonUtils.toResponse(ResultCode.SUCC, null);
	}
	@ApiOperation(value = "获取任务", httpMethod="POST")
	@PostMapping(value = "/queryTaskList", produces = {"application/json;charset=UTF-8"})
	public String queryTaskList(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		PageInfo<FrontTask> frontTaskPageInfo = frontTaskService.queryTaskList(record);
		return FastJsonUtils.toResponse(ResultCode.SUCC, frontTaskPageInfo);
	}

	@ApiOperation(value = "获取高危任务", httpMethod="POST")
	@PostMapping(value = "/queryHeightTaskList", produces = {"application/json;charset=UTF-8"})
	public String queryHeightTaskList(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		List<FrontTaskNode> frontTaskNodes = frontTaskNodeService.queryHeightTaskList(record);
		return FastJsonUtils.toResponse(ResultCode.SUCC, frontTaskNodes);
	}
}
