package com.example.ToDoList.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {

    private Integer id;

    private Instant createdAt;

    private Instant updatedAt;
}
