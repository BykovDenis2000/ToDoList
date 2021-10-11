package com.example.demo.service;

import com.example.demo.model.ToDoList;
import com.example.demo.model.User;
import com.example.demo.model.Note;
import com.example.demo.repos.ToDoListRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.repos.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceIml implements ToDoService {

    @Autowired
    NotesRepo notesRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ToDoListRepo toDoListRepo;


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

    @Override
    public String toDoListToString() {
        StringBuilder builder = new StringBuilder("ToDo List:\n");
        for(Note note : notesRepo.findAll()){
            builder.append(note.getText() + "\n");
        }
        return builder.toString();
    }

    @Override
    public void createToDoList(String name) {
        toDoListRepo.save(new ToDoList(name));
    }

    @Override
    public void saveToDoList(ToDoList toDoList) {
        toDoListRepo.save(toDoList);
    }

    @Override
    public List<ToDoList> findByUserId(String userId) {
        List<ToDoList> toDoLists = new ArrayList<>();
        for(ToDoList toDoList : toDoListRepo.findAll()){
            if(toDoList.getUserId().equals(userId)){
                toDoLists.add(toDoList);
            }
        }
        return toDoLists;
    }


}
