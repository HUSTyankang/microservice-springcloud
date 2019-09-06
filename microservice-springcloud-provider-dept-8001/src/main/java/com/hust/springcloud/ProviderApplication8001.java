package com.hust.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  //注册进eureka
@EnableDiscoveryClient //服务发现，对外暴露服务
public class ProviderApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication8001.class,args);
    }
}
