package com.cn.taskManager.domain.mapper;

import com.cn.taskManager.domain.pojo.SysStructure;
import com.cn.taskManager.domain.pojo.SysStructureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysStructureMapper {
    int countByExample(SysStructureExample example);

    int deleteByExample(SysStructureExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysStructure record);

    int insertSelective(SysStructure record);

    List<SysStructure> selectByExample(SysStructureExample example);

    SysStructure selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysStructure record, @Param("example") SysStructureExample example);

    int updateByExample(@Param("record") SysStructure record, @Param("example") SysStructureExample example);

    int updateByPrimaryKeySelective(SysStructure record);

    int updateByPrimaryKey(SysStructure record);
}