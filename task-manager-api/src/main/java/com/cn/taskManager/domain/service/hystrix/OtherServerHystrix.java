package com.cn.taskManager.domain.service.hystrix;

import com.cn.taskManager.domain.service.remote.OtherServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Evan
 * @date 2018/4/11
 */
@Component
public class OtherServerHystrix implements OtherServer {
    @Override
    public String getInfo(@RequestBody String userPhoneNum) {
        return null;
    }
}
