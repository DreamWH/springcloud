package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHystrixController {

    @Value("${server.port}")
    private String serverport;

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String paymentOk = paymentHystrixService.payment_ok(id);
        System.out.println(paymentOk);
        return paymentOk;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String paymentTimeout = paymentHystrixService.payment_timeout(id);
        System.out.println(paymentTimeout);
        return paymentTimeout;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String circuitBreaker = paymentHystrixService.paymentCircuitBreaker(id);
        System.out.println(circuitBreaker);
        return circuitBreaker;
    }
}
