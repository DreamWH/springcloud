package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Integer id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign")
    public String paymentFeignTimeout(){
        System.out.println("hello");
        return paymentFeignService.paymentFeignTimeout();
    }
}
