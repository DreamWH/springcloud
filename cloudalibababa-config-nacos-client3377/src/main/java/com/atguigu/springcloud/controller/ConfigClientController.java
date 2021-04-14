package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持动态刷新功能
public class ConfigClientController {

    @Value("${spring.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }

    /**
     * @Author wangjun
     * @Description 测试接口
     * @Date  2021/4/13
     * @Param [params, params2]
     * @return java.lang.String
    **/
    public String getTestString(String params,String params2){
        return params + params2;
    }

}
