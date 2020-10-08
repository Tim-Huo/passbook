package com.timhuo.passbook.passbook.advice;

import com.timhuo.passbook.passbook.vo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 全局异常处理
 * @author: Tim_Huo
 * @created: 2020/10/08 20:28
 */
@ControllerAdvice
public class  GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorInfo<String> errorHandler(HttpServletRequest request, Exception exception) throws Exception{
        ErrorInfo<String> info = new ErrorInfo<String>();
        info.setCode(ErrorInfo.ERROR);
        info.setMessage(exception.getMessage());
        info.setData("DO Not Have Return Data");
        info.setUrl(request.getRequestURI().toString());

        return info;
    }
}
