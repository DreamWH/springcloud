package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderMessageController {

    @Autowired
    private IMessageProducer iMessageProducer;

    @GetMapping("/message/{value}")
    public void testMessage(@PathVariable("value") String value){
        iMessageProducer.send(value);
    }

}
