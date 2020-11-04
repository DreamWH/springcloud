package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class MessageProducerImpl implements IMessageProducer {

    @Autowired
    private Source source;

    @Override
    public void send(String message) {
        System.out.println(message);
        source.output().send(MessageBuilder.withPayload(message).build());
    }
}
