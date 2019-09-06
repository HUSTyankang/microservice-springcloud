package com.hust.springcloud.service;

import com.hust.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {
    boolean addDept(Dept dept);
    Dept findById(Long id);
    List<Dept> findAll();
}
