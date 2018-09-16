package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysConfig;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(value = "SysConfig-Service")
public interface SysConfigService extends IService<SysConfig> {

}
