package com.cn.taskManager.common.utils;

import org.springframework.cglib.beans.BeanCopier;

/**
 *
 * @author Evan
 * @date 18/03/05
 */
public class CglibBeanUtils {

    /**
     * 深度copy,只copy名称和类型都相同的属性
     * @param target
     * @param source
     */
    public static void copy(Object target, Object source) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

}
