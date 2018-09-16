package com.cn.taskManager.domain.service.backend;

import com.baomidou.mybatisplus.service.IService;
import com.cn.taskManager.domain.entity.SysPost;

import java.util.List;

public interface SysPostService extends IService<SysPost> {

	List<SysPost> getDataList(String name);

}
