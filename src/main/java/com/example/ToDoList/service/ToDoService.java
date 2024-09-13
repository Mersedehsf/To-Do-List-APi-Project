package com.example.ToDoList.service;

import com.example.ToDoList.config.ApplicationConfig;
import com.example.ToDoList.config.jwt.JwtFilter;
import com.example.ToDoList.exception.ServiceException;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.model.enums.Role;
import com.example.ToDoList.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Predicate;


@Service
public class ToDoService extends AbstractService<ToDoEntity, ToDoRepository> {


    @Override
    public void create(ToDoEntity toDoEntity) {
        try {
            UserAuthenticationEntity loggedInUser = JwtFilter.loggedInUser;
            ToDoEntity newToDo = new ToDoEntity(toDoEntity.getTitle(), toDoEntity.getDescription(), loggedInUser);
            repository.save(newToDo);

        } catch (ServiceException e) {
            throw new ServiceException("logged in user not found");
        }

    }


    @Override
    public void update(ToDoEntity toDoEntity) {
        ToDoEntity foundedToDo =  repository.findById(toDoEntity.getId()).orElse(null);
        if(foundedToDo.getUserAuthenticationEntity().getId().equals(JwtFilter.loggedInUser.getId()) && Objects.nonNull(foundedToDo)){
            if (Objects.nonNull(toDoEntity.getDescription())){
                foundedToDo.setDescription(toDoEntity.getDescription());
            }
            if (Objects.nonNull(toDoEntity.getTitle())){
                foundedToDo.setTitle(toDoEntity.getTitle());
            }
            repository.save(foundedToDo);
        }
        else {
            throw new ServiceException("todo item was not found");
        }


    }

    @Override
    public void delete(Integer id) {
        ToDoEntity foundedToDo = repository.findById(id).orElse(null);
        if (Objects.nonNull(foundedToDo) && JwtFilter.loggedInUser.getId().equals(foundedToDo.getUserAuthenticationEntity().getId())){
            repository.delete(foundedToDo);
        }
        else {
            throw new ServiceException("forbidden");
        }

    }

    public ToDoEntity getByTitle(String title) {
        UserAuthenticationEntity loggedInUser = JwtFilter.loggedInUser;
        ToDoEntity toDoEntity = repository.findByTitle(title);
        if (Objects.nonNull(title) && toDoEntity.getUserAuthenticationEntity().getId().equals(loggedInUser.getId())) {
            return toDoEntity;
        } else {
            throw new ServiceException("A todo with this title was not found");
        }


    }
}
