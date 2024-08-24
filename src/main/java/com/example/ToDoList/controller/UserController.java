package com.example.ToDoList.controller;


import com.example.ToDoList.model.UserAuthenticationEntity;
import com.example.ToDoList.model.dto.UserDto;
import com.example.ToDoList.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserAuthenticationEntity, UserDto, UserService>{


    @PostMapping("/createUser")
    public void create(@RequestBody UserDto userDto){
        service.create(mapper.dtoToEntity(userDto));
    }







}
