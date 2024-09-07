package com.example.ToDoList.service;

import com.example.ToDoList.config.ApplicationConfig;
import com.example.ToDoList.config.jwt.JwtFilter;
import com.example.ToDoList.config.jwt.JwtService;
import com.example.ToDoList.exception.ServiceException;
import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.model.enums.Role;
import com.example.ToDoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService extends AbstractService<UserAuthenticationEntity, UserRepository> implements UserDetailsService{

    @Autowired
    JwtService jwtService;

    @Override
    public void create(UserAuthenticationEntity userAuthenticationEntity){
        userAuthenticationEntity.setPassword(ApplicationConfig.passwordEncoder().encode(userAuthenticationEntity.getPassword()));
        repository.save(userAuthenticationEntity);
    }

    public UserAuthenticationEntity findByName(String name) throws Exception {
        UserAuthenticationEntity userAuthenticationEntity = repository.findByName(name);
        if (Objects.nonNull(userAuthenticationEntity)){
            return userAuthenticationEntity;
        }
        else {
            throw new ServiceException("user with this name was not found");
        }

    }

    public UserAuthenticationEntity findByEmail(String email){
        UserAuthenticationEntity userAuthenticationEntity = repository.findByEmail(email);
        if(Objects.nonNull(userAuthenticationEntity)){
            return userAuthenticationEntity;
        }
        else {
            throw new ServiceException("user with this email was not found");
        }

    }

    @Override
    public void update(UserAuthenticationEntity userAuthenticationEntity) {

        UserAuthenticationEntity foundedUser = repository.findById(userAuthenticationEntity.getId())
                .orElseThrow(() -> new ServiceException("User not found"));

        if ((JwtFilter.loggedInUser.getRole().equals(Role.ADMIN) || JwtFilter.loggedInUser.getId().equals(foundedUser.getId())) ){
            if (Objects.nonNull(userAuthenticationEntity.getEmail())){
                foundedUser.setEmail(userAuthenticationEntity.getEmail());
            }
            if(Objects.nonNull(userAuthenticationEntity.getName())){
                foundedUser.setName(userAuthenticationEntity.getName());
            }
            if(Objects.nonNull(userAuthenticationEntity.getRole())){
                foundedUser.setRole(userAuthenticationEntity.getRole());
            }
            if (Objects.nonNull(userAuthenticationEntity.getPassword())){
                foundedUser.setPassword(ApplicationConfig.passwordEncoder().encode(userAuthenticationEntity.getPassword()));
            }
            repository.save(foundedUser);

        }

        else {
            throw new ServiceException("forbidden");
        }

    }

    @Override
    public void delete(Integer id) {
        UserAuthenticationEntity foundedUser = repository.findById(id)
                .orElseThrow(() -> new ServiceException("User not found"));
        if (JwtFilter.loggedInUser.getRole().equals(Role.ADMIN) || JwtFilter.loggedInUser.getId().equals(foundedUser.getId())) {
            repository.delete(foundedUser);
        }
        else {
            throw new ServiceException("forbidden");
        }

    }

    public String login(UserAuthenticationEntity userAuthenticationEntity) throws Exception {
        UserAuthenticationEntity foundedUser = findByEmail(userAuthenticationEntity.getEmail());
        if (ApplicationConfig.passwordEncoder().matches(userAuthenticationEntity.getPassword(), foundedUser.getPassword())) {
            String token = jwtService.generateToken(userAuthenticationEntity);
            return token;
        }
        else {
            throw new Exception("User is not authenticated");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails =  findByEmail(email);
        if (Objects.nonNull(userDetails)){
            return userDetails;
        }
        else {
            throw new ServiceException("userDetails with this username(email) does not exists.");
        }
    }
}
