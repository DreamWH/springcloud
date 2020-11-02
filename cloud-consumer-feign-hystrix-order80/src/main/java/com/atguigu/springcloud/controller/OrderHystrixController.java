package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "order_error_method")
public class OrderHystrixController {

    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        String paymentOk = orderHystrixService.payment_ok(id);
        System.out.println(paymentOk);
        return paymentOk;
    }

    /**
     * payment_timeout 超过1.5s，则会执行payment_timeoutHandler这个方法
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "payment_timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String payment_timeout(@PathVariable("id") Integer id){
        String paymentTimeout = orderHystrixService.payment_timeout(id);
        System.out.println(paymentTimeout);
        return paymentTimeout;
    }
    public String payment_timeoutHandler(@PathVariable("id") Integer id){
        return "订单80模块，对方支付系统繁忙请稍后再试,或者查看自己是否运行出错！！！";
    }

    public String order_error_method(){
        return "系统错误，请稍后再试！！！";
    }

}
