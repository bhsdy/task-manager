package com.cn.taskManager.common.exptions;

/**
 * @author Evan
 * @date 2018/3/5
 */
public class SystemException extends AbstractApiException {
    public SystemException(String exceptionContext) {
        super(exceptionContext);
    }
}
