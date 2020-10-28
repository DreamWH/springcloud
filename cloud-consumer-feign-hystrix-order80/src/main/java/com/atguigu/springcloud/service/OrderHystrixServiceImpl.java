package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderHystrixServiceImpl implements OrderHystrixService {
    @Override
    public String payment_ok(Integer id) {
        return null;
    }

    @Override
    public String payment_timeout(Integer id) {
        return null;
    }
}
