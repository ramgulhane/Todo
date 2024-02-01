package com.todo.controller;

import com.todo.dto.TodoDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoUpdateDto;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public TodoResponseDto create(@RequestBody TodoDto todoDto) {
        return todoService.create(todoDto);
    }

    @GetMapping
    public List<TodoResponseDto> find(){
        return todoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TodoResponseDto findOne(@PathVariable Integer id){
        return todoService.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public TodoResponseDto update(@PathVariable Integer id, @RequestBody TodoUpdateDto todoUpdateDto){
        return todoService.udpate( id,  todoUpdateDto);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
         todoService.delete(id);
    }

}
