package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Note;

public interface ToDoService {

    void createNote(String text);

    void deleteNote(int id);

    Iterable<Note> listNotes();

    void saveNote(Note note);

    void saveUser(User user);
}
