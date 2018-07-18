package com.cn.taskManager.domain.mapper;

import com.cn.taskManager.common.utils.MyMapper;
import com.cn.taskManager.domain.entity.SysRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRuleMapper extends MyMapper<SysRule> {
    List<SysRule> selectByStatus(@Param("status") Integer status);

    List<SysRule> selectInIds(@Param("ruleIds") String ruleIds,@Param("status") Integer status);

}
