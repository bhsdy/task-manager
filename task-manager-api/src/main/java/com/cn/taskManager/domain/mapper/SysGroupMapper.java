package com.cn.taskManager.domain.mapper;

import com.cn.taskManager.common.utils.MyMapper;
import com.cn.taskManager.domain.entity.SysGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysGroupMapper extends MyMapper<SysGroup> {
    /**
     * 查询分组信息
     * @param userId 用户ID
     * @param status 状态
     * @return
     */
    List<SysGroup> selectByUserId(@Param("userId") String userId, @Param("status") Byte status);

}
