package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.SysDict;
import com.cn.taskManager.domain.mapper.backend.SysDictMapper;
import com.cn.taskManager.domain.service.backend.SysDictService;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

}
