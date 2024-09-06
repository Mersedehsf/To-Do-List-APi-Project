package com.example.ToDoList.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO extends BaseDto{


    @NotNull(message = "Title cannot be null")
    private String title;

    private String description;


}
