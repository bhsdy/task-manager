package com.cn.taskManager.dto;

import com.cn.taskManager.common.enums.Whether;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @author Evan
 * @date 2018/3/5
 */
@ApiModel
@Data
@ToString
public class StudentDTO {
    @ApiModelProperty(value="学生姓名", required = true)
    private String studtntName;

    @ApiModelProperty(value="学生年龄")
    private Integer age;

    @ApiModelProperty(value="是否有效")
    private Whether effective;

    public String getStudtntName() {
        return studtntName;
    }

    public void setStudtntName(String studtntName) {
        this.studtntName = studtntName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Whether getEffective() {
        return effective;
    }

    public void setEffective(Whether effective) {
        this.effective = effective;
    }
}
