package com.hust.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hust.springcloud"})
public class ConsumerApplicationFeign {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationFeign.class,args);
    }
}
