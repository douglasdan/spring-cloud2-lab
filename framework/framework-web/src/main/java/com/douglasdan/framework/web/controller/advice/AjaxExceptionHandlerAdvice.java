package com.douglasdan.framework.web.controller.advice;

import com.douglasdan.framework.common.constants.Constants;
import com.douglasdan.framework.common.error.BizRuntimeException;
import com.douglasdan.framework.common.error.ErrorCodeUtil;
import com.douglasdan.framework.common.model.AjaxResult;
import com.douglasdan.framework.common.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas.Dan
 * @date 2018-08-21
 */
@ConditionalOnWebApplication
@ControllerAdvice(annotations = RestController.class)
public class AjaxExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(AjaxExceptionHandlerAdvice.class);

    @ExceptionHandler({Exception.class, BizRuntimeException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ErrorCodeUtil.printException(logger, e);

        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(new String(new AjaxResult<>().fail(e).toString().getBytes(Constants.HTTP_CHARSET)));
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Object exceptionHandler(MissingServletRequestParameterException e, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        ErrorCodeUtil.printException(logger, e);

        AjaxResult<?> ajaxResult = new AjaxResult<>().fail();
        ajaxResult.setCode(Constants.REQUEST_PARAMETERS_INVALID).setMessage(e.toString());
        return ajaxResult;
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    public Object exceptionHandler(BindException e, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        ErrorCodeUtil.printException(logger, e);

        BindingResult bindingResult = e.getBindingResult();
        AjaxResult<?> ajaxResult = new AjaxResult<>().fail();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            ajaxResult.setMessage(objectError.getDefaultMessage());
        }
        ajaxResult.setCode(Constants.REQUEST_PARAMETERS_INVALID);
        return ajaxResult;
    }

}
