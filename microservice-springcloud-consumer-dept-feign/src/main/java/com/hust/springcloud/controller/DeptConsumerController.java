package com.hust.springcloud.controller;

import com.hust.springcloud.entities.Dept;
import com.hust.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.List;

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
