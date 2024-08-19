package com.example.ToDoList.repository;

import com.example.ToDoList.model.UserAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAuthenticationEntity,Integer> {

}
