package com.example.ToDoList.repository;

import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuthenticationEntity,Integer> {

    UserAuthenticationEntity findByName(String name);

}
