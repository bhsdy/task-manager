package com.cn.taskManager.domain.mapper;

import com.cn.taskManager.common.utils.MyMapper;
import com.cn.taskManager.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends MyMapper<SysMenu> {
    /**
     * 根据ruleIds查询菜单信息
     * @param ruleIds 权限id
     * @param status 状态值
     * @return List<SysAdminMenu>
     */
    List<SysMenu> selectInRuleIds(@Param("ruleIds") String ruleIds, @Param("status") int status);

}
