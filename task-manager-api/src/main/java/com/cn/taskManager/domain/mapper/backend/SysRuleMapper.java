package com.cn.taskManager.domain.mapper.backend;

import com.cn.taskManager.common.mybatisplus.SuperMapper;
import com.cn.taskManager.domain.entity.SysRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysRuleMapper extends SuperMapper<SysRule> {
    List<SysRule> selectByStatus(@Param("status") Integer status);

    List<SysRule> selectInIds(@Param("ruleIds") String ruleIds,@Param("status") Integer status);

}
