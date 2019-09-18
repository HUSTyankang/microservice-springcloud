package com.hust.springcloud.controller;

import com.hust.springcloud.entities.Dept;
import com.hust.springcloud.service.DeptService;
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
    public Dept get(@PathVariable("id") long id){
        return deptService.findById(id);
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
}

