package com.mall.zhangxuan.exception;


public class ErrorException extends RuntimeException {

    private int httpStatus;
    public ErrorException(String msg){}

    public ErrorException(String msg, int httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;

    }

    public Integer getCode(){
        return httpStatus;
    }

    public void setCode(int httpStatus) {
        this.httpStatus = httpStatus;
    }

}
