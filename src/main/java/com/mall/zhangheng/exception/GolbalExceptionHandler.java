package com.mall.zhangheng.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GolbalExceptionHandler {


    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public ErrorResponseModel golbalExceptionHander(ErrorException e, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(e.getCode());
        return ErrorResponseModel.builder()
                .httpStatus(e.getCode())
                .message(e.getMessage())
                .url(request.getRequestURL().toString()).build();

    }

}
