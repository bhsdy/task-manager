package com.cn.taskManager.common.exptions;

import com.cn.taskManager.common.JsonResult;
import com.cn.taskManager.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author Evan
 * @date 2018/4/11
 */
@ControllerAdvice
public class GlobalException {
    Logger logger = LoggerFactory.getLogger(GlobalException.class);
    private JsonResult result = new JsonResult();

    @ExceptionHandler(value = ValidateException.class)
    @ResponseBody
    public JsonResult validateException(ValidateException ex){
        result = JsonResult.renderMsg(ResultCode.PARAM_ERROR, ex.getMessage());
        logger.error(ResultCode.PARAM_ERROR.getMsg(), ex);
        return result;
    }

    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseBody
    public JsonResult userNotExistException(UserNotExistException ex){
        result = JsonResult.renderMsg(ResultCode.USER_NOT_EXIST, ex.getMessage());
        logger.error(ResultCode.USER_NOT_EXIST.getMsg(), ex);
        return result;
    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public JsonResult systemException(SystemException ex){
        result = JsonResult.renderMsg(ResultCode.FAILED, ex.getMessage());
        logger.error(ResultCode.FAILED.getMsg(), ex);
        return result;
    }

    @ExceptionHandler(value = AbstractApiException.class)
    @ResponseBody
    public JsonResult abstractApiException(AbstractApiException ex){
        result = JsonResult.renderMsg(ResultCode.FAILED, ex.getMessage());
        logger.error(ResultCode.FAILED.getMsg(), ex);
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult exception(Exception ex){
        result = JsonResult.renderMsg(ResultCode.FAILED, ex.getMessage());
        logger.error(ResultCode.FAILED.getMsg(), ex);
        return result;
    }

}
