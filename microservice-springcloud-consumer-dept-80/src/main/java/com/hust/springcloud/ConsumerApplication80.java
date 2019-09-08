package com.hust.springcloud;

import com.hust.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//在启动该微服务时就能去加载我们的自定义Ribbon配置类，从而使配置生效
//自定义的Ribbon配置类不能放在@ComponentScan所扫描的当前包及子包下，否则该自定义类就会被所有的Ribbon客户端所共享。
@RibbonClient(name = "MICROSERVICE-SPRINGCLOUD-DEPT",configuration = MyselfRule.class)
public class ConsumerApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication80.class,args);
    }
}
