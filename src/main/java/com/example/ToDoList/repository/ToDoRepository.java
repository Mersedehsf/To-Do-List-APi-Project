package com.example.ToDoList.repository;

import com.example.ToDoList.model.entity.ToDoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity,Integer> {

    ToDoEntity findByTitle(String title);

    Page<ToDoEntity> findAllByUserAuthenticationEntityId(Integer userId, Pageable pageable);

}
