package com.cn.taskManager.domain.service.frontend.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.FrontTaskNode;
import com.cn.taskManager.domain.mapper.frontend.FrontTaskNodeMapper;
import com.cn.taskManager.domain.service.frontend.FrontTaskNodeService;
import org.springframework.stereotype.Service;

@Service
public class FrontTaskNodeServiceImpl extends ServiceImpl<FrontTaskNodeMapper, FrontTaskNode> implements FrontTaskNodeService {

}
