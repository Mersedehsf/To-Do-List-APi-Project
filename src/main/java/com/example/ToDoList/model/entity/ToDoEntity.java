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




}
