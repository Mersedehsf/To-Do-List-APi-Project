package com.example.ToDoList.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User_Authentication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true)
    @Email
    private String email;

    @Column(name = "password",nullable = false,length = 20)
    @Size(min = 10, max = 20)
    private String password;

}
