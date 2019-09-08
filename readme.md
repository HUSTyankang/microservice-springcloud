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
>springboot的学习：
>>对dao、service、controller三层的学习与理解；  
>>对mybatis的学习。 
> 
>springcloud的学习：
>>REST方式实现微服务之间的调用；  
>>eureka实现微服务之间的注册于发现；  
>>ribbon实现负载均衡： 
>>>1.轮询：consumer使用@LoadBalanced实现了轮询；    
>>>2.随机：使用RandomRule()；   
```java  
@Bean    
public IRule myRule(){   
    return new RandomRule(); //用随机算法替代默认的轮询算法。  
}
```


