package com.cn.taskManager.domain.service;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysGroup;
import com.cn.taskManager.domain.entity.SysMenu;
import com.cn.taskManager.domain.entity.SysMenu;
import com.cn.taskManager.domain.mapper.SysGroupMapper;
import com.cn.taskManager.domain.mapper.SysMenuMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuService extends BaseServiceImpl<SysMenu> {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysGroupMapper sysGroupMapper;


	@Override
	public Mapper<SysMenu> getMapper() {
		return sysMenuMapper;
	}

	/**
	 * 获取用户对应的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getTreeMenuByUserId(String userId){
		//查看用户对应未禁用的菜单
		List<SysMenu> menusList = getMenusByUserId(userId, (byte)1);
		//处理树菜单
		List<SysMenu> menusTreeList = this.buildByRecursiveTree(menusList);
		return menusTreeList;
	}

	/**
	 * 根据用户id查询所属的菜单信息
	 * @param userId 用户id
	 * @param status 状态 0：禁用，1：启用，null：全部
	 * @return
	 */
	private List<SysMenu> getMenusByUserId(String userId, Byte status) {
		List<SysMenu> menusList;
		//判断是否为管理员
		if(userId.equals("1")) {
			SysMenu menu = new SysMenu();
			menu.setStatus(status);
			menusList = this.select(menu);
		} else {
			//查询分组
			List<SysGroup> groupsList = sysGroupMapper.selectByUserId(userId, status);
			StringBuffer ruleIds = new StringBuffer();
			for(SysGroup group : groupsList) {
				if(ruleIds.length() == 0) {
					ruleIds.append(group.getRules());
				} else {
					ruleIds.append(",").append(group.getRules());
				}
			}
			//查询菜单
			menusList =  sysMenuMapper.selectInRuleIds(ruleIds.toString(), 1);
		}

		return menusList;
	}

	/**
     * 使用递归方法建树
     * @param rootSysMenus 原始的数据
     * @return
     */
	private List<SysMenu> buildByRecursiveTree(List<SysMenu> rootSysMenus){
	    List<SysMenu> trees = new ArrayList<SysMenu>();
	    for(SysMenu menu : rootSysMenus) {
	    	if ("0".equals(menu.getPid().toString())) {
                trees.add(getChild(menu,rootSysMenus, 1));
            }
	    }
	    return trees;
	}

	/**
	 * 递归查找子菜单
	 *
	 * @param treeMenu
	 *            当前菜单id
	 * @param treeNodes
	 *            要查找的列表
	 * @param level
	 * 			  级别
	 * @return
	 */
	private SysMenu getChild(SysMenu treeMenu, List<SysMenu> treeNodes, int level) {
		treeMenu.setSelected(false);
		treeMenu.setLevel(level);
		for (SysMenu it : treeNodes) {
			if (treeMenu.getId().equals(it.getPid())) {
				if (treeMenu.getChild() == null) {
					treeMenu.setChild(new ArrayList<>());
				}
				treeMenu.getChild().add(getChild(it, treeNodes, level + 1));
			}
		}
		return treeMenu;
	}


	/**
	 * 查询对应用户Id的菜单
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getDataList(String userId, Byte status) {
		List<SysMenu> rootSysMenus = this.getMenusByUserId(userId, status);
		Map<String, String> fields = Maps.newHashMap();
		fields.put("cid", "id");
		fields.put("fid", "pid");
		fields.put("name", "title");
		fields.put("fullname", "title");
		List<Map<String, Object>> rawList = Lists.newArrayList();
		rootSysMenus.forEach((m)->{
			rawList.add(BeanToMapUtil.convertBean(m));
		});
		Category cate = new Category(fields, rawList);
//		return cate.getList(Integer.valueOf("0"));
		return cate.getRawList();
	}

}
