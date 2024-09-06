package com.example.ToDoList.service;

import com.example.ToDoList.config.jwt.JwtFilter;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.repository.ToDoRepository;
import org.springframework.stereotype.Service;


@Service
public class ToDoService extends AbstractService<ToDoEntity, ToDoRepository >{

    @Override
    public void create(ToDoEntity toDoEntity){
        UserAuthenticationEntity loggedInUser = JwtFilter.loggedInUser;
        ToDoEntity newToDo = new ToDoEntity(toDoEntity.getTitle(),toDoEntity.getDescription(),loggedInUser);
        repository.save(newToDo);

    }


    @Override
    public void update(ToDoEntity toDoEntity) {

    }
}
