package com.example.ToDoList.controller;

import com.example.ToDoList.model.AbstractEntity;
import com.example.ToDoList.service.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractController<E extends AbstractEntity,D,S extends AbstractService<E,? extends JpaRepository<E,Integer>>>{
}
