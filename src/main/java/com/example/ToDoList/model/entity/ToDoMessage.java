package com.example.ToDoList.model.entity;

public class ToDoMessage {

    private String title;

    public ToDoMessage() {
    }

    public ToDoMessage(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ToDoMessage{" +
                "title='" + title + '\'' +
                '}';
    }
}
