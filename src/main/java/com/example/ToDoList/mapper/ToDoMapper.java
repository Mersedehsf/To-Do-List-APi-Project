package com.example.ToDoList.mapper;

import com.example.ToDoList.model.dto.ToDoDTO;
import com.example.ToDoList.model.entity.ToDoEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface ToDoMapper extends AbstractMapper<ToDoEntity, ToDoDTO>{
}
