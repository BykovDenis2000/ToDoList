package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    private int listId;

    public Note(){}

    public Note(String text)
    {
        this.text = text;
    }

    public int getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setUserId(ToDoList listId) {this.listId = listId;}

}
