package com.example.Thymeleaf.Models;

public class ToDoModel {

    private int id;
    private String name;

    public ToDoModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ToDoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ToDoModel{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
