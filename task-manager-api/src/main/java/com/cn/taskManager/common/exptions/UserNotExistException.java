package com.cn.taskManager.common.exptions;

/**
 *
 * @author Evan
 * @date 18/03/05
 */
public class UserNotExistException extends AbstractApiException {

    public UserNotExistException(String exceptionContext) {
        super(exceptionContext);
    }
}
