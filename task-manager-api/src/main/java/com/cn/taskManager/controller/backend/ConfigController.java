package com.cn.taskManager.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.mapper.backend.SysConfigMapper;
import com.cn.taskManager.domain.service.backend.SysConfigService;
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
@RequestMapping("/admin")
@Api(value = "SysConfigController", description = "系统配置接口")
public class ConfigController extends CommonController {
	@Autowired
	private SysConfigService sysConfigService;

	@ApiOperation(value = "获取配置", httpMethod="POST")
	@PostMapping(value = "/configs", produces = {"application/json;charset=UTF-8"})
	public String configs(@RequestBody(required=false) SysConfig record, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<>();
		if(record != null){
			EntityWrapper<SysConfig> ew = new EntityWrapper<>();
			ew.where("group={0}",record.getGroup());
			List<SysConfig> configs = sysConfigService.selectList(ew);
			for (SysConfig c : configs) {
				data.put(c.getName(), c.getValue());
			}
		}
		return FastJsonUtils.resultSuccess("200", "查询配置成功", data);
	}
}
