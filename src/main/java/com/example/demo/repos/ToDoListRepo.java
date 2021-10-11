package com.example.demo.repos;

import com.example.demo.model.ToDoList;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepo extends JpaRepository<ToDoList,Integer>  {

}
