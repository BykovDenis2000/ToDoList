package com.example.demo.controller;

import com.example.demo.model.ToDoList;
import com.example.demo.model.User;
import com.example.demo.service.MailSender;
import com.example.demo.service.ToDoService;
import com.example.demo.model.Note;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/notes/{id}")
    public String listNotes(@PathVariable Integer id,Model model, @AuthenticationPrincipal User user) {
        Iterable<Note> listNotes = toDoService.listNotes();
        model.addAttribute("listNotes", listNotes);
        model.addAttribute("user", user);
        return "todolist";
    }

    @GetMapping("/add_todo_list")
    public String showAddToDoForm(Model model) {

        model.addAttribute("todolist", new ToDoList());

        return "add_todo_list_form";
    }

    @PostMapping("/process_todo_list_add")
    public String processToDoListAdd(ToDoList toDoList,@AuthenticationPrincipal User user) {
        toDoList.setUserId(user);
        toDoService.saveToDoList(toDoList);

        return "redirect:/notes";
    }

    @GetMapping("/todolists")
    public String toDoLists(Model model, @AuthenticationPrincipal User user) {
        List<ToDoList> listNotes = toDoService.findByUserId(user.getUserId());
        model.addAttribute("listNotes", listNotes);
        model.addAttribute("user", user);
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

    @PostMapping("/send_email")
    public String sendEmail(@AuthenticationPrincipal User user) {
        mailSender.send(user.getEmail(),"ToDo List",toDoService.toDoListToString());
        return "redirect:/notes";
    }
}
