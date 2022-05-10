package com.jtfr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);

    @GetMapping("/get")
    public String get(){
        LOGGER.info("trace get");
        LOGGER.info("debug get");
        LOGGER.info("info get");
        LOGGER.info("warning get");
        LOGGER.info("error get");
        return "LoggerController.test()";
    }
}
