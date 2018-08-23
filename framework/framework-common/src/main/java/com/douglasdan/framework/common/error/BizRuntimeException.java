package com.douglasdan.framework.common.error;

import com.alibaba.fastjson.JSONObject;
import com.douglasdan.framework.common.constants.Constants;

/**
 * @author Douglas.Dan
 */
public class BizRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -179240901263372980L;

    private String errorCode;

    private String message;

    public BizRuntimeException() {
        this.errorCode = Constants.UNKNOWN_ERROR;
        this.message = "";
    }

    public BizRuntimeException(String errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = ErrorCodeUtil.generateErrorMessage(errorCode, args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public BizRuntimeException setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public String getMessage() {
        return message != null ? message : super.getMessage();
    }

    public BizRuntimeException setMessage(String message) {
        this.message = message;
        return this;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        json.put("errorCode", errorCode);
        json.put("message", message);
        return json;
    }
}
