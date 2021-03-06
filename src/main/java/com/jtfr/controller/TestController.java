package com.jtfr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/post")
    public String post() {
        return "TestController.post()";
    }

    @GetMapping("/get")
    public String get() {
        return "TestController.get()";
    }
}
