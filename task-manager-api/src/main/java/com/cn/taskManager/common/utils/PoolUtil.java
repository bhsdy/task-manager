package com.cn.taskManager.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Evan
 * @date 2018/4/27
 */
public class PoolUtil {

    private PoolUtil(){}

    private static class PoolUtilInside{
        public static ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("tlpay-pool-%d").build();
        public static final ExecutorService executorService = new ThreadPoolExecutor(5,10,60,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1024),nameThreadFactory);
    }



    public static ExecutorService getPoolExecutor(){
        return PoolUtilInside.executorService;
    }
}
