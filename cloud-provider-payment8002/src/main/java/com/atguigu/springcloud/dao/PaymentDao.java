package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

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
