package com.example.ToDoList.controller;


import com.example.ToDoList.model.dto.UserDto;
import com.example.ToDoList.model.entity.UserAuthenticationEntity;
import com.example.ToDoList.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController<UserAuthenticationEntity, UserDto, UserService>{


    @PostMapping("/register")
    public void register(@RequestBody @Valid UserDto userRegisterDto){
        service.create(mapper.dtoToEntity(userRegisterDto));
    }


    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto read(@RequestParam("name") String name) throws Exception {
        return mapper.entityToDto(service.findByName(name));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void update(@RequestBody UserDto userRegisterDto){
        service.update(mapper.dtoToEntity(userRegisterDto));
    }


    @DeleteMapping("/physicalDelete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }


    @GetMapping("/login")
    public String login(@RequestBody UserDto userDto) throws Exception {
       return service.login(mapper.dtoToEntity(userDto));
    }



}
