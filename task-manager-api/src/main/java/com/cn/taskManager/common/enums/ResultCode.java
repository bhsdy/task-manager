package com.cn.taskManager.common.enums;

/**
 *
 * @author Evan
 * @date 2018/3/5
 */
public enum ResultCode {
    SUCC("200", "成功"),
    FAILED("1001", "失败"),
    PARAM_ERROR("1002", " 请求参数异常"),
    NO_DATA("1003", "没有数据"),
    NO_RIGHT("1004", "没有权限访问资源"),
    OTHER("3001", "其他"),
    USER_NOT_EXIST("4002", "用户不存在");


    private String code;

    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
