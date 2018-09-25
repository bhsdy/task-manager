package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.SysConfig;
import com.cn.taskManager.domain.mapper.backend.SysConfigMapper;
import com.cn.taskManager.domain.service.backend.SysConfigService;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

}
