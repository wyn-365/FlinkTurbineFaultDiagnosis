package com.wang.springcloud.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 王一宁
 * @date 2020/2/4 16:56
 */
@Configuration
public class ConfigBean {

    //
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
