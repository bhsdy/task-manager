package com.cn.taskManager.common.exptions;

/**
 *
 * @author Evan
 * @date 2018/03/05
 */
public abstract class AbstractApiException extends RuntimeException {

    protected String exceptionContext;

    public AbstractApiException(String exceptionContext) {
        super(exceptionContext);
        this.exceptionContext = exceptionContext;
    }

}
