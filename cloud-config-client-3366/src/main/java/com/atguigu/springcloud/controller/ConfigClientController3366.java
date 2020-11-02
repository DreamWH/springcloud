package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController3366 {

    @Value("${gbs.host}")
    private String file;

    @Value("${eureka.instance.hostname}")
    private String port;

    @GetMapping("/file")
    private String fileName(){
        return file;
    }

    @GetMapping("/name")
    private String testName(){
        return "hello:" + port;
    }
}
