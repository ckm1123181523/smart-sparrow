package com.jtfr.exception;

import com.jtfr.enums.ApplicationExceptionEnum;

/**
 * 系统异常类
 */
public class ApplicationException extends RuntimeException {

    private int code;

    private ApplicationException(ApplicationExceptionEnum applicationExceptionEnum) {
        super(applicationExceptionEnum.desc());
        this.code = applicationExceptionEnum.code();
    }


    public static ApplicationException of(ApplicationExceptionEnum applicationExceptionEnum) {
        return new ApplicationException(applicationExceptionEnum);
    }

}
