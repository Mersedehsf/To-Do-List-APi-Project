package com.example.ToDoList.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

import lombok.*;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto extends BaseDto{

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "password cannot be null")
    @Length(min = 4,max = 10,message = "Password must be between 4 and 15 characters")
    private String password;
}
