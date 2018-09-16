package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysRule;

import java.util.List;
import java.util.Map;

public interface SysRuleService extends IService<SysRule> {

	/**
	 * 根据用户名获取rule数组
	 * @param userId 用户id
	 */
	List<SysRule> getTreeRuleByUserId(String userId);

	/**
	 * 给树状规则表处理成 module-controller-action
	 * @return treeNodes
	 */
	List<String> rulesDeal(List<SysRule> treeNodes);

	/**
	 * 列表页面
	 * @param userId 用户id
	 * @param type  类型 tree,其它
	 * @return
	 */
	List<Map<String, Object>> getDataList(String userId,String type);
}
