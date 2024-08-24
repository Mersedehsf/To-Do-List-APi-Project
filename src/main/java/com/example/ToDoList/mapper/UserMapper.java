package com.example.ToDoList.mapper;

import com.example.ToDoList.model.UserAuthenticationEntity;
import com.example.ToDoList.model.dto.UserDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface UserMapper extends AbstractMapper<UserAuthenticationEntity, UserDto>{
}
