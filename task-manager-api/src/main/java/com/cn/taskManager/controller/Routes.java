package com.cn.taskManager.controller;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 访问路径
 * @author Evan
 * @date 2018/03/05
 */
@Component
public class Routes {
    private Map<String, String> paths = new ConcurrentHashMap<>();


    public Routes() throws IllegalAccessException {
        Field[] fields = this.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            paths.put(field.getName(), (String) field.get(this));
        }
    }

    public String path(String key) {
        return paths.get(key);
    }
    public static final String HOME = "/";

    public static final String BACKEND = "/backend/";
    public static final String FRONTEND = "/frontend/";

    /**
     * 学生
     */
    public static final String STUDENT_ADD = BACKEND+"/student/add";
    public static final String STUDENT_DETAIL = BACKEND+"/student/detail";
    public static final String STUDENT_LIST = FRONTEND +"/student/list";


}
