package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 获取用户对应的菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> getTreeMenuByUserId(String userId);

	/**
	 * 查询对应用户Id的菜单
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getDataList(String userId, Byte status);

}
