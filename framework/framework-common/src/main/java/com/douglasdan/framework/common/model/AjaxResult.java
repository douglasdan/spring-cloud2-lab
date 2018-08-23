package com.douglasdan.framework.common.model;

/**
 * Created by Douglas.Dan on 2018-08-21.
 */
import com.douglasdan.framework.common.constants.Constants;
import com.douglasdan.framework.common.error.BizRuntimeException;
import com.douglasdan.framework.common.util.HttpHelper;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.UUID;

public class AjaxResult<T> extends Model {

    private static final long serialVersionUID = -1483992046638883042L;

    private T data;

    private String requestId;

    private String code;

    private String message;

    private Boolean success;

    public AjaxResult() {
        super();
        this.requestId = HttpHelper.generateRequestId();
    }

    public AjaxResult(T data) {
        this();
        setSuccess(true);
        setCode(Constants.SUCCESS);
        setMessage("");
        setData(data);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public AjaxResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AjaxResult<T> fail(Exception e) {
        this.success = false;

        if (e instanceof BizRuntimeException) {
            this.setCode(((BizRuntimeException) e).getErrorCode());
            this.setMessage(e.getMessage());
        } else {
            this.setCode(Constants.UNKNOWN_ERROR);
            this.setMessage(e.getMessage());
        }
        return this;
    }

    public AjaxResult<T> fail() {
        this.success = false;
        return this;
    }

    public AjaxResult<T> success() {
        this.success = true;
        return this;
    }

}
