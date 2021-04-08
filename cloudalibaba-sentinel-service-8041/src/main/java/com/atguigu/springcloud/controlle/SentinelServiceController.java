package com.atguigu.springcloud.controlle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelServiceController {

    @GetMapping("/getA")
    public String testA(){
        return "----testA";
    }

    @GetMapping("/getB")
    public String testB(){
        return "----testB";
    }
}
