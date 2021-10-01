package com.example.demo.repos;

import com.example.demo.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepo extends CrudRepository<Note, Integer> {


}
