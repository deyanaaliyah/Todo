package kea.springrest.demo.controller;

import kea.springrest.demo.model.Todo;
import kea.springrest.demo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    TodoRepository toDoRepository;

    public TodoController(TodoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    // Read all tasks
    @GetMapping("/todo")
    public Iterable<Todo> index(){

        return toDoRepository.findAll();
    }

    // Create a task
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/todo")
    public ResponseEntity<String> create(@ModelAttribute Todo todoToBeAdded){
        Todo todo = toDoRepository.save(todoToBeAdded);

        return ResponseEntity.status(201).header("Location", "/todo/" + todo.getId()).body("{'msg' : 'task created'}");
    }


    // Delete a task by ID
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        toDoRepository.deleteById(id);
        // PostMan test :
        return ResponseEntity.status(200).body("{'msg' : 'deleted'}");
    }
}