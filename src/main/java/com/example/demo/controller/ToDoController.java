package com.example.demo.controller;

import com.example.demo.service.ToDoService;
import com.example.demo.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ToDoController {
    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService service) {
        this.toDoService = service;
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam(value = "id",required = false) int id){

        toDoService.deleteNote(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String listNotes(Model model) {
        List<Note> listNotes = toDoService.listNotes();
        model.addAttribute("listNotes", listNotes);

        return "todolist";
    }

    @GetMapping("/addnote")
    public String showAddForm(Model model) {
        model.addAttribute("note", new Note());

        return "add_form";
    }

    @PostMapping("/process_add")
    public String processAdd(Note note) {
        toDoService.saveNote(note);

        return "redirect:/notes";
    }
}
