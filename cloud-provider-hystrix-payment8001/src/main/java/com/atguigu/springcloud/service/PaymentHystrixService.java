package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class PaymentHystrixService {

    public String payment_ok(Integer id){
        return "线程名称：" + Thread.currentThread().getName() + ",id:" + id + ",返回成功";
    }

    /**
     * payment_timeout 这个方法要是响应时间超过3秒，则会执行fallbackMethod中指定的方法
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod="payment_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String payment_timeout(Integer id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名称：" +  Thread.currentThread().getName() + ",id:" + id + ",返回成功,耗时（ms）" + id;
    }

    public String payment_timeoutHandler(Integer id){
        return "线程名称：" +  Thread.currentThread().getName() + ",时间(ms):" + id + "8001系统繁忙,o(╥﹏╥)o,响应时间过长！！！！";
    }

    //=======服务熔断测试
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启熔断器，也就是保险丝
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求的次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//窗口期，10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//10s内请求失败率,如果10s内有6次请求失败，则开始熔断机制
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("****id,不能是负数");
        }
        String uuid = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t,调用成功,流水号是:" + uuid;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id,不能是负数,请重新输入id!!!!!";
    }
}
