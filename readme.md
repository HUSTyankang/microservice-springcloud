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
修改了[RandomRule的源代码](https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RandomRule.java)对微服务进行轮询策略的访问，每个服务访问五次；
```java
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule(){
        return new MyRule_YanKang();
    }
}
```
四、feign：旨在使编写Java Http客户端变得更容易  
在feign的实现下，我们只需要创建一个接口并使用注解方式来配置http请求，即可完成对服务提供方的接口的绑定，简化了使用springcloud ribbon是自动封装微服务调用客户端的开发量。   
&emsp;在microservice-springcloud-api中的com.hust.springcloud添加service包：  
```java
@Configuration
@FeignClient(value = "microservice-springcloud-dept")
public interface DeptClientService {

//    @GetMapping(value = "/dept/get/{id}")
    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    Dept get(@PathVariable("id") long id);

//    @GetMapping(value = "/dept/list")
    @RequestMapping(value = "/dept/list",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
    List<Dept> list();

//    @PostMapping(value = "/dept/add")
    @RequestMapping(value = "/dept/add",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    boolean add(Dept dept);
}
```
&emsp;在microservice-springcloud-consumer-dept-feign中的controller下：
```java
@RestController
public class DeptConsumerController {

    @Autowired
    DeptClientService deptClientService;

//    @PostMapping(value = "/consumer/dept/add")
    @RequestMapping(value = "/consumer/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptClientService.add(dept);

    }

//    @GetMapping(value = "/consumer/dept/get/{id}")
    @RequestMapping(value = "/consumer/dept/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id){
        return deptClientService.get(id);
    }

//    @GetMapping(value = "/consumer/dept/list")
    @RequestMapping(value = "/consumer/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return deptClientService.list();
    }
}
```
&emsp;feign的主启动类下添加@EnableFeignClients(basePackages = {"com.hust.springcloud"})注解：
```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hust.springcloud"})
public class ConsumerApplicationFeign {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationFeign.class,args);
    }
}
```


