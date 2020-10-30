package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${gbs.host}")
    private String file;

    @GetMapping("/file")
    private String fileName(){
        return file;
    }

    @GetMapping("/name")
    private String testName(){
        return "hello";
    }
}
