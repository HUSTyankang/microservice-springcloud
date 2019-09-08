# 项目介绍
## 项目的目的
本项目是对微服务的学习。
## 项目结构
microservise-springcloud是父工程；  
microservice-springcloud-api是公用模块；  
microservice-springcloud-eureka-server-8761、microservice-springcloud-eureka-server-8762、microservice-springcloud-eureka-server-8763构成
eureka集群，他们之间相互注册；  
microservice-springcloud-provider-dept-8001、microservice-springcloud-provider-dept-8002、microservice-springcloud-provider-dept-8003是三个微服务提供者；  
microservice-springcloud-consumer-dept-80是微服务消费者。
## 学习知识
### springboot的学习：
对dao、service、controller三层的学习与理解；  
对mybatis的学习。 
### springcloud的学习：
一、REST方式实现微服务之间的调用；  
二、eureka实现微服务之间的注册于发现；  
三、ribbon实现负载均衡：   
&emsp;1.轮询：consumer使用@LoadBalanced实现了轮询；    
&emsp;2.随机：使用RandomRule()；   
```java
@Bean    
public IRule myRule(){   
    return new RandomRule(); //用随机算法替代默认的轮询算法。  
}
```
&emsp;3.Retry：使用RetryRule()；
```java
@Bean
 public IRule myRule(){
    //先轮询，如果服务宕机，则会再指定的时间内重试，获取可用的服务。
    return new RetryRule();
}
```
&emsp;4.自定义：主启动类上加@RibbonClient
```java
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
```
&emsp;自定义的Ribbon配置类：
```java
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
```

