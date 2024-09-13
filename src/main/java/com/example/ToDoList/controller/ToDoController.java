package com.example.ToDoList.controller;

import com.example.ToDoList.model.dto.ToDoDTO;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoController extends AbstractController<ToDoEntity, ToDoDTO, ToDoService>{

    @PostMapping("/create")
    public void register(@RequestBody @Valid ToDoDTO toDoDTO){
        service.create(mapper.dtoToEntity(toDoDTO));
    }

    @GetMapping("/get")
    public ToDoDTO get(@RequestParam String title){
        return mapper.entityToDto(service.getByTitle(title));
    }

    @PutMapping("/update")
    public void update(@RequestBody ToDoDTO toDoDTO){
        service.update(mapper.dtoToEntity(toDoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }


}
