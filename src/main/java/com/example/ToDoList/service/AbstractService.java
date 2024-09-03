package com.example.ToDoList.service;

import com.example.ToDoList.model.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends JpaRepository<E, Integer>> {

    @Autowired
    protected R repository;

    public void create(E entity) {
        repository.save(entity);
    }

    public Optional<E> findById(Integer id) throws Exception {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void update(E e);

    public void delete(Integer id){
        try {
            E entity = repository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);//todo how to add message to this?
            if (Objects.nonNull(entity)) {
                repository.delete(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
