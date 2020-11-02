package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBanlance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBanlance loadBanlance;

    @Autowired
    private DiscoveryClient discoveryClient;

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://cloud-payment-service";

    @GetMapping("/port")
    public String port(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBanlance.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/port",String.class);
    }

    @PostMapping("/add")
    public CommonResult<Payment> add(@RequestBody Payment payment){
        System.out.println("hello");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/add",payment,CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class );
    }

    @GetMapping("/discovery")
    public Object getDiscovery(){
        return restTemplate.getForObject(PAYMENT_URL + "/discovery",Object.class);
    }

}
