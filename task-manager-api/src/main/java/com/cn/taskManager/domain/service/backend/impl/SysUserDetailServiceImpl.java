package com.cn.taskManager.domain.service.backend.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cn.taskManager.domain.entity.SysUserDetail;
import com.cn.taskManager.domain.mapper.backend.SysUserDetailMapper;
import com.cn.taskManager.domain.service.backend.SysUserDetailService;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailServiceImpl extends ServiceImpl<SysUserDetailMapper, SysUserDetail> implements SysUserDetailService {

}
