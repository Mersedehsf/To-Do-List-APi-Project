package com.example.ToDoList.controller;

import com.example.ToDoList.model.dto.ToDoDTO;
import com.example.ToDoList.model.entity.ToDoEntity;
import com.example.ToDoList.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


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

    @GetMapping("/findAll")
    public Page<ToDoDTO> findAll(@RequestParam int page, @RequestParam int size){
        return new PageImpl<> (service.getAll(page-1,size).stream().map(dto->mapper.entityToDto(dto)).collect(Collectors.toList()));
    }

}
