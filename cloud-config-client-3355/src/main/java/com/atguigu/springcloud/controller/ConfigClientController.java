package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Value("${ruoyi.profile}")
    private String file;

    @GetMapping("/file")
    private String fileName(){
        return file;
    }
}