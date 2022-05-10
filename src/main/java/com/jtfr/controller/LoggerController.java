package com.jtfr.controller;

import com.alibaba.fastjson.JSON;
import com.jtfr.pojo.LoggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    private static Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);

    @GetMapping("/get")
    public String get(@RequestParam String ok){
        LOGGER.info("ok = {}", ok);
        LOGGER.trace("trace get");
        LOGGER.debug("debug get");
        LOGGER.info("info get");
        LOGGER.warn("warn get");
        LOGGER.error("error get");
        return "LoggerController.get()";
    }

    @GetMapping("/get2")
    public String get(){
        LOGGER.trace("trace get");
        LOGGER.debug("debug get");
        LOGGER.info("info get");
        LOGGER.warn("warn get");
        LOGGER.error("error get");
        return "LoggerController.get2()";
    }

    @PostMapping("/post")
    public String post(LoggerEntity loggerEntity){
        LOGGER.info(JSON.toJSONString(loggerEntity));
        return "LoggerController.post()";
    }
}
