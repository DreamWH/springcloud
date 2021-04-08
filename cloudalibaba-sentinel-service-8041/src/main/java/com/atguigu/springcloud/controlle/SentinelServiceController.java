package com.atguigu.springcloud.controlle;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SentinelServiceController {

    @GetMapping("/getA")
    @SentinelResource(value = "getResource",blockHandler = "handleException")
    public String testA(){
        return "----testA" + Thread.currentThread().getName();
    }

    @GetMapping("/getB")
    public String testB(){
        System.out.println(Thread.currentThread().getName());
        int i = 1 / 0;
        return "----testB" + Thread.currentThread().getName();
    }

    public String handleException(BlockException blockException){
        return "handleException";
    }
}
