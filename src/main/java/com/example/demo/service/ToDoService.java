package com.example.demo.service;

import com.example.demo.model.Note;

import java.util.List;

public interface ToDoService {

    void createNote(String text);

    void deleteNote(int id);

    List<Note> listNotes();

    void saveNote(Note note);
}
