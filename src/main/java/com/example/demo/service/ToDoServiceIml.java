package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Note;
import com.example.demo.repos.UserRepo;
import com.example.demo.repos.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceIml implements ToDoService {

    @Autowired
    NotesRepo notesRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public void createNote(String text) {

        notesRepo.save(new Note(text));
    }

    @Override
    public void deleteNote(int id) {

        notesRepo.deleteById(id);
    }

    @Override
    public Iterable<Note> listNotes() {

        return notesRepo.findAll();
    }

    @Override
    public void saveNote(Note note) {
        notesRepo.save(note);
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }
}
