package com.cn.taskManager.domain.service.backend;

import com.cn.taskManager.common.BaseServiceImpl;
import com.cn.taskManager.common.utils.BeanToMapUtil;
import com.cn.taskManager.common.utils.Category;
import com.cn.taskManager.domain.entity.SysGroup;
import com.cn.taskManager.domain.entity.SysRule;
import com.cn.taskManager.domain.mapper.backend.SysGroupMapper;
import com.cn.taskManager.domain.mapper.backend.SysRuleMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysRuleService extends BaseServiceImpl<SysRule> {

	@Autowired
	private SysRuleMapper sysRuleMapper;
	@Autowired
	private SysGroupMapper sysGroupMapper;

	@Override
	public Mapper<SysRule> getMapper() {
		return sysRuleMapper;
	}

	/**
	 * 根据用户名获取rule数组
	 * @param userId 用户id
	 */
	public List<SysRule> getTreeRuleByUserId(String userId) {
		List<SysRule> rulesList = getRulesByUserId(userId);
		//处理树
		List<SysRule> rulesTreeList = this.buildByRecursiveTree(rulesList);
		return rulesTreeList;
	}


	/**
     * 使用递归方法建树
     * @param rootSysRules 原始的数据
     * @return
     */
	private List<SysRule> buildByRecursiveTree(List<SysRule> rootSysRules){
	    List<SysRule> trees = new ArrayList<SysRule>();
	    if(CollectionUtils.isEmpty(rootSysRules)){
	    	return trees;
		}
		SysRule SysRule = rootSysRules.get(0);

		rootSysRules.forEach(item -> {
			if ("0".equals(item.getPid()+"")) {
				trees.add(getChild(item,rootSysRules, 1));
			}
		});
//	    for(SysRule menu : rootSysRules) {
//	    	if ("0".equals(menu.getPid().toString())) {
//                trees.add(getChild(menu,rootSysRules, 1));
//            }
//	    }
	    return trees;
	}

	/**
	 * 递归查找子菜单
	 *
	 * @param treeMenu
	 *            当前菜单id
	 * @param treeNodes
	 *            要查找的列表
	 * @return
	 */
	private SysRule getChild(SysRule treeMenu, List<SysRule> treeNodes, int level) {
		for (SysRule it : treeNodes) {
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
	 * 给树状规则表处理成 module-controller-action
	 * @return treeNodes
	 */
	public List<String> rulesDeal(List<SysRule> treeNodes) {
		List<String> ruleStr = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(treeNodes)) {
			for (SysRule root : treeNodes) {
				if (CollectionUtils.isNotEmpty(root.getChild())) {
					for (SysRule c1 : root.getChild()) {
						if (CollectionUtils.isNotEmpty(c1.getChild())) {
							for (SysRule c2 : c1.getChild()) {
								ruleStr.add(root.getRuleName() + "-"  +c1.getRuleName() + "-" + c2.getRuleName());
							}
						}else {
							ruleStr.add(root.getRuleName() + "-"  +c1.getRuleName());
						}
					}
				} else {
					ruleStr.add(root.getRuleName());
				}

			}
		}
		return ruleStr;
	}

	/**
	 * 列表页面
	 * @param userId 用户id
	 * @param type  类型 tree,其它
	 * @return
	 */
	public List<Map<String, Object>> getDataList(String userId,String type) {
		List<SysRule> rulesList = getRulesByUserId(userId);
		if(type != null && "tree".equals(type)) {
			//处理树
			rulesList = this.buildByRecursiveTree(rulesList);
			List<Map<String, Object>> rawList = Lists.newArrayList();
			rulesList.forEach((m)->{
				Map<String, Object> map = BeanToMapUtil.convertBean(m);
				map.put("check", false);
				rawList.add(map);
			});
			return rawList;
		}else {
			Map<String, String> fields = Maps.newHashMap();
			fields.put("cid", "id");
			fields.put("fid", "pid");
			fields.put("name", "title");
			fields.put("fullname", "title");
			List<Map<String, Object>> rawList = Lists.newArrayList();
			rulesList.forEach((m)->{
				rawList.add(BeanToMapUtil.convertBean(m));
			});
			Category cate = new Category(fields, rawList);
//			return cate.getList(Integer.valueOf("0"));
			return cate.getRawList();
		}

	}


	/**
	 * 根据用户id查询所属的权限信息
	 * @param userId 用户id
	 * @return
	 */
	private List<SysRule> getRulesByUserId(String userId) {
		List<SysRule> rulesList = Lists.newArrayList();
		boolean equals = userId.equals("1");
		//判断是否为管理员
		if(userId.equals("1")) {
			rulesList = sysRuleMapper.selectByStatus(1);
		} else {
			//查询分组
			List<SysGroup> groupsList = sysGroupMapper.selectByUserId(userId, (byte) 1);
			StringBuffer ruleIds = new StringBuffer();
			for(SysGroup group : groupsList) {
				if(ruleIds.length() == 0) {
					ruleIds.append(group.getRules());
				} else {
					ruleIds.append(",").append(group.getRules());
				}
			}
			//查询权限
			rulesList = sysRuleMapper.selectInIds(ruleIds.toString(), 1);
		}
		return rulesList;
	}
}
