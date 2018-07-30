package com.cn.taskManager.domain.mapper.frontend;

import com.cn.taskManager.common.utils.MyMapper;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrontTaskNodeMapper extends MyMapper<FrontTaskNode> {
    List<FrontTaskNode> queryHeightTaskList();
}
