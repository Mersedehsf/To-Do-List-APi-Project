package com.example.ToDoList.service;

import com.example.ToDoList.config.ApplicationConfig;
import com.example.ToDoList.model.UserAuthenticationEntity;
import com.example.ToDoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends AbstractService<UserAuthenticationEntity, UserRepository>{

    @Override
    public void create(UserAuthenticationEntity userAuthenticationEntity){
        userAuthenticationEntity.setPassword(ApplicationConfig.passwordEncoder().encode(userAuthenticationEntity.getPassword()));
        repository.save(userAuthenticationEntity);
    }

    public Optional<UserAuthenticationEntity> findByName(String name) throws Exception {
        try {
            return Optional.ofNullable(repository.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






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
                foundedUser.setPassword(ApplicationConfig.passwordEncoder().encode(userAuthenticationEntity.getPassword()));
            }
            repository.save(foundedUser);

        }



    }
}
