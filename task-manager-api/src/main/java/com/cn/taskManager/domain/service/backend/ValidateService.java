package com.cn.taskManager.domain.service.backend;

import com.cn.taskManager.common.exptions.ValidateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Evan
 * @date 18/03/05
 */
@Service
public class ValidateService {

    private HttpServletRequest request;

    public ValidateService(HttpServletRequest request) {
        this.request = request;
    }

    public ValidateService() {

    }

    public Object check(String paramName, Object paramValue) {

        if (paramValue ==null){
            handleParamError(paramName);
        }

        return paramValue;
    }

    public String checkAndPrepare(String paramName, String paramValue) {

        if (StringUtils.isBlank(paramValue)){
            handleParamError(paramName);
        }

        return paramValue;
    }

    public Long checkAndPrepareLong(String paramName, String paramValue) {

        if (StringUtils.isBlank(paramValue)){
            handleParamError(paramName);
        }

        Long result = null;

        try {
            result = Long.valueOf(paramValue);
        } catch (Exception ex) {
            handleParamError(paramName);
        }

        return result;
    }

    public Integer checkAndPrepareInt(String paramName, String paramValue) {

        if (StringUtils.isBlank(paramValue)){
            handleParamError(paramName);
        }

        Integer result = null;

        try {
            result = Integer.valueOf(paramValue);
        } catch (Exception ex) {
            handleParamError(paramName);
        }

        return result;
    }

    private void handleParamError(String errorContext) {
        throw new ValidateException(errorContext);
    }
}
