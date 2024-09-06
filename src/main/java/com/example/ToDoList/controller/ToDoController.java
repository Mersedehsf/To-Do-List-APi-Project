package com.example.ToDoList.controller;

import com.example.ToDoList.model.dto.ToDoDTO;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoController extends AbstractController<ToDoEntity, ToDoDTO, ToDoService>{

    @PostMapping("/create")
    public void register(@RequestBody @Valid ToDoDTO toDoDTO){
        service.create(mapper.dtoToEntity(toDoDTO));
    }


}
