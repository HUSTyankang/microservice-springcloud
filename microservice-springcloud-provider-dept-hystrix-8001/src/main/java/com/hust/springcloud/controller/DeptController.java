package com.hust.springcloud.controller;

import com.hust.springcloud.entities.Dept;
import com.hust.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DeptController {
    @Autowired
    DeptService deptService;
    @Autowired
    DiscoveryClient discoveryClient;

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        System.out.println(dept);
        return deptService.addDept(dept);

    }

    @GetMapping(value = "/dept/get/{id}")
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "processHystrixGet")
    public Dept get(@PathVariable("id") long id){
        Dept dept = this.deptService.findById(id);
        if(null == dept){
            throw new RuntimeException("该ID：" + id + "没有对应的信息");
        }
        return dept;
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list(){
        return deptService.findAll();
    }

    @GetMapping(value = "/dept/discovery")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        log.info("find servivces:"+list);

        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("MICROSERVICE-SPRINGCLOUD-DEPT");
        for(ServiceInstance item : serviceInstanceList){
            log.info("appname:"+item.getServiceId()+",host:"+item.getHost()+",port:"+item.getPort()+",uri:"+item.getUri());
        }
        return this.discoveryClient;
    }

    public Dept processHystrixGet(@PathVariable("id") long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的信息，null--@HystrixCommand").setDb_source("no this database in MySQL");
    }
}

