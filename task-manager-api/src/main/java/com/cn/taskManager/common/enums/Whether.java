package com.cn.taskManager.common.enums;

/**
 * @date 2018/02/13
 * @author Evan
 */
public enum Whether {
    YES("是"),
    NO("否");

    protected String value;


    Whether(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}