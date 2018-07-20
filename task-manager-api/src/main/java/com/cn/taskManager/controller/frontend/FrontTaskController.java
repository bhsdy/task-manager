package com.cn.taskManager.controller.frontend;

import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.enums.ResultCode;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.mapper.backend.SysConfigMapper;
import com.cn.taskManager.domain.service.frontend.FrontTaskService;
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
 * 系统配置 控制层
 * @author leo.aqing
 */
@RestController
@RequestMapping("/api")
@Api(value = "FrontTaskController", description = "系统配置接口")
public class FrontTaskController extends CommonController {
	@Autowired
	private FrontTaskService frontTaskService;

	@ApiOperation(value = "获取配置", httpMethod="POST")
	@PostMapping(value = "/save", produces = {"application/json;charset=UTF-8"})
	public String save(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		int saveResult = frontTaskService.save(record);
		if(saveResult == 0) {
			return FastJsonUtils.toResponse(ResultCode.FAILED, null);
		}
		return FastJsonUtils.toResponse(ResultCode.SUCC, null);
	}
}
