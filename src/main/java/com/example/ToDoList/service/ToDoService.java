package com.example.ToDoList.service;

import com.example.ToDoList.config.jwt.JwtFilter;
import com.example.ToDoList.exception.ServiceException;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.repository.ToDoRepository;
import org.springframework.stereotype.Service;


@Service
public class ToDoService extends AbstractService<ToDoEntity, ToDoRepository >{


    // todo should i handle my exceptions with try catch or with if? hosseini once told "if" is better
    @Override
    public void create(ToDoEntity toDoEntity){
        try {
            UserAuthenticationEntity loggedInUser = JwtFilter.loggedInUser;
            ToDoEntity newToDo = new ToDoEntity(toDoEntity.getTitle(),toDoEntity.getDescription(),loggedInUser);
            repository.save(newToDo);

        } catch (ServiceException e) {
            throw new ServiceException("logged in user not found");
        }

    }


    @Override
    public void update(ToDoEntity toDoEntity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
