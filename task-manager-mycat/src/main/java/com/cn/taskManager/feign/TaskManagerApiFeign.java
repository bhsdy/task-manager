package com.cn.taskManager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "task-manager-api")
public interface TaskManagerApiFeign {
    @RequestMapping(value = "/admin/user/queryUser",method = RequestMethod.GET)
    public String queryUser();
}
