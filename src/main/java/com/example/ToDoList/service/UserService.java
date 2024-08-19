package com.example.ToDoList.service;

import com.example.ToDoList.config.SecurityConfig;
import com.example.ToDoList.model.UserAuthenticationEntity;
import com.example.ToDoList.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class UserService extends AbstractService<UserAuthenticationEntity, UserRepository>{




    @Override
    public void update(UserAuthenticationEntity userAuthenticationEntity) {
        Optional<UserAuthenticationEntity> user =  repository.findById(userAuthenticationEntity.getId());
        if (user.isPresent()){
            UserAuthenticationEntity foundedUser = user.get();
            if (Objects.nonNull(userAuthenticationEntity.getEmail())){
                foundedUser.setEmail(userAuthenticationEntity.getEmail());
            }
            if(Objects.nonNull(userAuthenticationEntity.getName())){
                foundedUser.setName(userAuthenticationEntity.getName());
            }
            if (Objects.nonNull(userAuthenticationEntity.getPassword())){
                foundedUser.setPassword(SecurityConfig.passwordEncoder().encode(userAuthenticationEntity.getPassword()));
            }
            repository.save(foundedUser);

        }



    }
}
