package com.cn.taskManager.domain.service.remote;

import com.cn.taskManager.domain.service.hystrix.OtherServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  主要是调用其它内部服务接口
 * @author Evan
 * @date 2018/4/11
 */
@FeignClient(name= "OTHER-SERVER" , fallback = OtherServerHystrix.class)
@Component
public interface OtherServer {


    @RequestMapping(value="/other/info", method = RequestMethod.POST)
    @ResponseBody
    String getInfo(@RequestBody String userPhoneNum);
}
