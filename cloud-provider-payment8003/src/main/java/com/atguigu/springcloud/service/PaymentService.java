package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    /**
     * 查询
     * @param id
     * @return
     */
    public Payment getPaymentById(@Param("id") Integer id);

    /**
     * 插入
     * @param payment
     * @return
     */
    public int add(Payment payment);
}
