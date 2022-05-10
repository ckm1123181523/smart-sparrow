package com.jtfr.controller;

import com.jtfr.enums.ApplicationExceptionEnum;
import com.jtfr.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @GetMapping("/get")
    public String getException(){
        LOGGER.info("ExceptionController.get() executes successfully");
        throw ApplicationException.of(ApplicationExceptionEnum.PARAMS_NULL);
    }

    @PostMapping("/post")
    public String post(){
        LOGGER.info("ExceptionController.post() executes successfully");
        throw ApplicationException.of(ApplicationExceptionEnum.PARAMS_ERROR);
    }



}
