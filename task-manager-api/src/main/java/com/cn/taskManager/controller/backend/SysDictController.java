package com.cn.taskManager.controller.backend;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.enums.ResultCode;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysDict;
import com.cn.taskManager.domain.service.backend.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 数据字典接口
 * @author John
 */
@RestController
@RequestMapping("/admin/sysDict")
@Api(value = "SysDictController", description = "数据字典接口")
public class SysDictController extends CommonController {
	@Autowired
	private SysDictService sysDictService;

	@ApiOperation(value = "获取配置", httpMethod="POST")
	@PostMapping(value = "/queryList", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String queryList(@RequestBody SysDict record, HttpServletRequest request) {
		List<SysDict> sysDictList;
		try {
			EntityWrapper<SysDict> ew = new EntityWrapper<>();
			ew.where("dict_group_code={0} and status={1}",record.getDictGroupCode(),record.getStatus());
			sysDictList = sysDictService.selectList(ew);
		}catch (Exception x){
			return FastJsonUtils.toResponse(ResultCode.FAILED,null);
		}
		return FastJsonUtils.toResponse(ResultCode.SUCC, sysDictList);
	}
}
