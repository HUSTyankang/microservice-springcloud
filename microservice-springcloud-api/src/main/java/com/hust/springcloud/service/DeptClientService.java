package com.hust.springcloud.service;

import com.hust.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@Configuration
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
