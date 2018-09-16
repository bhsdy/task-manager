package com.cn.taskManager.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时任务
 * @author Evan
 * @date 2018/3/6
 */
@Component
public class SimpleSchedule {

   /* @Scheduled(cron = "0 0/10 * * * ?")
    private void run(){
        System.out.printf("我是定时任务", LocalDateTime.now());
    }*/
}
