package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Order83Controller {

    private final RestTemplate restTemplate;

    public Order83Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${service.url.nacos-user-service}")
    private String serviceURL;

    @GetMapping("/order/consumer/{id}")
    public String testOrder83(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serviceURL + "/nacos/payment/" + id, String.class);
    }

}
