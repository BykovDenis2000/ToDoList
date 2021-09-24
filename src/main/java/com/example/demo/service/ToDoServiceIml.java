package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repos.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoServiceIml implements ToDoService {

    @Autowired
    NotesRepo notesRepo;

    @Override
    public void createNote(String text) {
        notesRepo.save(new Note(text));
    }

    @Override
    public void deleteNote(int id) {
        for(Note note : notesRepo.findAll()){
            if(note.getId()==id){
                notesRepo.delete(note);
            }
        }
    }

    @Override
    public List<Note> listNotes() {
        List<Note> noteList = new ArrayList<>();
        for(Note note : notesRepo.findAll()){
            noteList.add(note);
        }
        return noteList;
    }

    @Override
    public void saveNote(Note note) {
        notesRepo.save(note);
    }
}
