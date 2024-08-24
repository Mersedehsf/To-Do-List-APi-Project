package com.example.ToDoList.model.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto{

    private String name;

    private String email;

    private String password;
}
