package com.cn.taskManager.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "KafkaController", description = "Kafka接口")
@RestController
public class KafkaController {
    private final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @ApiOperation(value = "新增", httpMethod="POST")
    @RequestMapping(value = "/send/{msg}", method = RequestMethod.GET)
    public String send(@PathVariable("msg") String msg){
        try {
            System.out.println("消息:" + JSON.toJSONString(msg));
            kafkaTemplate.send("test",JSON.toJSONString(msg));
            return "发送成功";
        } catch (Exception e) {
            logger.info("消息发送失败，原因："+e);
            e.printStackTrace();
            return "发送失败";
        }

    }

}
