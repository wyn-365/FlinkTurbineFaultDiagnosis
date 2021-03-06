package com.wang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 王一宁
 * @date 2020/2/4 16:40
 */
@SpringBootApplication
@EnableEurekaClient //开启后会自动注册Eureka服务中
@EnableDiscoveryClient //服务发现
@EnableCircuitBreaker //对hystrix熔断器开启支持
public class DeptProvider8001_Hystrix_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_Hystrix_App.class,args);
    }
}
