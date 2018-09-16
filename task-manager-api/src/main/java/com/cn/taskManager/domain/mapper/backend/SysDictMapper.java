package com.cn.taskManager.domain.mapper.backend;

import com.cn.taskManager.common.mybatisplus.SuperMapper;
import com.cn.taskManager.domain.entity.SysDict;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysDictMapper extends SuperMapper<SysDict> {
    /**
     * 查询数据字典列表
     */
    List<SysDict> querySysDictList(Map map);
}
