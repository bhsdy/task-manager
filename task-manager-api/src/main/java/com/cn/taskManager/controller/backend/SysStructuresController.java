package com.cn.taskManager.controller.backend;

import com.cn.taskManager.common.CommonController;
import com.cn.taskManager.common.utils.FastJsonUtils;
import com.cn.taskManager.domain.entity.SysStructure;
import com.cn.taskManager.domain.service.backend.SysStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统权限控制层
 * @author leo.aqing
 */
@RestController
@RequestMapping("/admin/structures")
@Api(value = "SysStructuresController", description = "系统权限接口")
public class SysStructuresController extends CommonController{
	@Autowired
	private SysStructureService sysStructureService;

	/**
	 * 列表
	 */
	@ApiOperation(value = "列表", httpMethod="GET")
	@RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String index(HttpServletRequest request) {
		List<Map<String, Object>> goups = sysStructureService.getDataList();
		return FastJsonUtils.resultSuccess("200", "成功", goups);
	}

	/**
	 * 读取
	 */
	@ApiOperation(value = "编辑", httpMethod="GET")
	@GetMapping(value = "edit/{id}", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String read(@PathVariable Integer id, HttpServletRequest request) {
		SysStructure goup = sysStructureService.selectByPrimaryKey(id);
		return FastJsonUtils.resultSuccess("200", "成功", goup);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "保存", httpMethod="POST")
	@PostMapping(value = "save", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String save(@RequestBody(required=false) SysStructure record,HttpServletRequest request) {
		SysStructure saveResult = sysStructureService.save(record);
		if(saveResult == null) {
			return FastJsonUtils.resultError("-200", "保存失败", null);
		}
		return FastJsonUtils.resultSuccess("200", "成功", null);
	}


	/**
	 * 更新
	 */
	@ApiOperation(value = "更新")
	@PostMapping(value = "update", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String update(@RequestBody(required=false) SysStructure record,HttpServletRequest request) {
		SysStructure saveResult = sysStructureService.save(record);
		if(saveResult == null) {
			return FastJsonUtils.resultError("-200", "更新失败", null);
		}
		return FastJsonUtils.resultSuccess("200", "更新成功", null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除")
	@DeleteMapping(value = "delete/{id}", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String delete(@PathVariable Integer id) {
		int row = sysStructureService.deleteByPrimaryKey(id);
		if(row == 0) {
			return FastJsonUtils.resultError("-200", "删除失败", null);
		}
		return FastJsonUtils.resultSuccess("200", "删除成功", null);
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "根据ids批量删除")
	@PostMapping(value = "deletes", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String deletes(@RequestBody Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>)params.get("ids");
		if (CollectionUtils.isEmpty(ids)) {
			return FastJsonUtils.resultError("-200", "操作失败", null);
		}
		try {
			for (int i = 0; i < ids.size(); i++) {
				sysStructureService.deleteByPrimaryKey(ids.get(i));
			}
		} catch (Exception e) {
			return FastJsonUtils.resultError("-200", "保存失败", null);
		}
		return FastJsonUtils.resultSuccess("200", "成功", null);
	}

	/**
	 * 启用或禁用
	 */
	@ApiOperation(value = "根据ids批量启用或禁用")
	@PostMapping(value = "enables", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String enables(@RequestBody Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>)params.get("ids");
		byte status = Byte.valueOf(params.get("status").toString());
		if (CollectionUtils.isEmpty(ids)) {
			return FastJsonUtils.resultError("-200", "操作失败", null);
		}
		try {
			for (int i = 0; i < ids.size(); i++) {
				SysStructure record = new SysStructure();
				record.setId(ids.get(0).toString());
				record.setStatus(status);
				sysStructureService.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			return FastJsonUtils.resultError("-200", "保存失败", null);
		}
		return FastJsonUtils.resultSuccess("200", "成功", null);
	}
}
