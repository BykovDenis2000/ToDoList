package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MailSender;
import com.example.demo.service.ToDoService;
import com.example.demo.model.Note;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ToDoController {
    private final ToDoService toDoService;
    private final MailSender mailSender;
    public ToDoController(ToDoService service, MailSender mailSender) {

        this.toDoService = service;
        this.mailSender = mailSender;
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam(value = "id",required = false) int id){

        toDoService.deleteNote(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String listNotes(Model model, @AuthenticationPrincipal User user) {
        Iterable<Note> listNotes = toDoService.listNotes();
        model.addAttribute("listNotes", listNotes);
        model.addAttribute("user", user);
        mailSender.send(user.getEmail(),"Blabla","hi denis");
        return "todolist";
    }

    @GetMapping("/addnote")
    public String showAddForm(Model model) {

        model.addAttribute("note", new Note());

        return "add_form";
    }

    @PostMapping("/process_add")
    public String processAdd(Note note,@AuthenticationPrincipal User user) {
        note.setUserId(user.getUserId());
        toDoService.saveNote(note);

        return "redirect:/notes";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        //model.addAttribute("User", new Note());

        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("User", new User());

        return "registration";
    }
    @PostMapping("/process_registration")
    public String processRegistration(User user) {
        toDoService.saveUser(user);
        return "redirect:/notes";
    }
}
