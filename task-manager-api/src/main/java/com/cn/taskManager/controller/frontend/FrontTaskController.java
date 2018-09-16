package com.cn.taskManager.controller.frontend;

import com.baomidou.mybatisplus.plugins.Page;
import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.enums.ResultCode;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.FrontTask;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.service.frontend.FrontTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

	@ApiOperation(value = "新增任务", httpMethod="POST")
	@PostMapping(value = "/addTask", produces = {"application/json;charset=UTF-8"})
	public String addTask(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		FrontTask frontTask;
			if(record == null){
				return FastJsonUtils.toResponse(ResultCode.FAILED, null);
			}
			if(StringUtils.isEmpty(record.getTaskName())){
				return FastJsonUtils.resultError("1001","任务名不能为空",null);
			}
			if(!CollectionUtils.isEmpty(record.getFrontTaskNodeList())){
				for (FrontTaskNode item : record.getFrontTaskNodeList()) {
					if(item.getTime() == null){
						return FastJsonUtils.resultError("1001","任务节点时间不能为空",null);
					}
				}
			}
		try {
			if(StringUtils.isEmpty(record.getId())){
				record.setId(getUuid());
				frontTask = frontTaskService.addTask(record);
			}else {
				frontTask = frontTaskService.editTask(record);
			}
			if(frontTask == null) {
				return FastJsonUtils.toResponse(ResultCode.FAILED, null);
			}
			return FastJsonUtils.toResponse(ResultCode.SUCC, null);

		} catch (Exception e) {
			return FastJsonUtils.toResponse(ResultCode.FAILED, null);
		}
	}
	@ApiOperation(value = "获取任务", httpMethod="POST")
	@PostMapping(value = "/queryTaskList", produces = {"application/json;charset=UTF-8"})
	public String queryTaskList(@RequestBody(required=false) FrontTask record, HttpServletRequest request) {
		Page<FrontTask> frontTaskPageInfo = frontTaskService.queryTaskList(record);
		return FastJsonUtils.toResponse(ResultCode.SUCC, frontTaskPageInfo);
	}
}
