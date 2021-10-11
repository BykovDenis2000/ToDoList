package com.example.demo.model;

import javax.persistence.*;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listId;
    private String listName;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;

    public ToDoList(){}

    public ToDoList(String listName){
        this.listName = listName;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserId() {
        return userId.getUserId();
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
