package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication_8761 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_8761.class,args);
    }
}
