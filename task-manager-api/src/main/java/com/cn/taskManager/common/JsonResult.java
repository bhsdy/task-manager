package com.cn.taskManager.common;

import com.cn.taskManager.common.enums.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author Evan
 * @date 17/10/30
 */
@Data
@ToString
public class JsonResult<T> {

    public String resultCode;
    public String msg;
    public Long size = 1L;
    public T result;

    public static JsonResult renderMsg(ResultCode code, Object data) {
        JsonResult result = new JsonResult();

        result.resultCode = code.getCode();
        result.msg = code.getMsg();
        result.result = data;

        return result;
    }

    public static JsonResult renderMsg(ResultCode code) {
        return renderMsg(code,null);
    }

    public static JsonResult renderMsg(Object data) {
        return renderMsg(ResultCode.SUCC,data);
    }


}
