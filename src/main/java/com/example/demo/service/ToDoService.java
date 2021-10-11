package com.example.demo.service;

import com.example.demo.model.ToDoList;
import com.example.demo.model.User;
import com.example.demo.model.Note;

import java.util.List;

public interface ToDoService {

    void createNote(String text);

    void deleteNote(int id);

    Iterable<Note> listNotes();

    void saveNote(Note note);

    void saveUser(User user);

    String toDoListToString();

    void createToDoList(String listName);

    void saveToDoList(ToDoList toDoList);

    List<ToDoList> findByUserId(String userId);
}
