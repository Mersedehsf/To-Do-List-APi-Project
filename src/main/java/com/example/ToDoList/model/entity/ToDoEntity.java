package com.example.ToDoList.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "to_do")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoEntity extends AbstractEntity{


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAuthenticationEntity userAuthenticationEntity;

    @Column(name = "completed",nullable = false)
    private Integer completed = 0;


    public ToDoEntity(String title, String description, UserAuthenticationEntity loggedInUser) {
        this.title = title;
        this.description = description;
        this.userAuthenticationEntity = loggedInUser;
    }
}
