package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFeign {

    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
