package com.example.ToDoList.repository;

import com.example.ToDoList.model.UserAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserRepository extends JpaRepository<UserAuthenticationEntity,Integer> {

}
