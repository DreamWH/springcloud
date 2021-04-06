package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Payment9001AlibabaController {

    @Value("${server.port}")
    public String port;

    @GetMapping("/nacos/payment/{number}")
    public String testNacosController(@PathVariable("number") Integer number){
        return "端口号是" + port + "；参数：" + number;
    }
}
