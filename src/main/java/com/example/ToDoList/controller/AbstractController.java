package com.example.ToDoList.controller;

import com.example.ToDoList.mapper.AbstractMapper;
import com.example.ToDoList.model.AbstractEntity;
import com.example.ToDoList.model.dto.BaseDto;
import com.example.ToDoList.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractController<E extends AbstractEntity,D extends BaseDto,S extends AbstractService<E,? extends JpaRepository<E,Integer>>>{

    @Autowired
    protected S service;

    @Autowired
    protected AbstractMapper<E,D> mapper;


}
