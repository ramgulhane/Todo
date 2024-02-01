package com.todo.dto;

import com.todo.entitiy.Todo;
import lombok.Data;

import java.util.Optional;

@Data
public class TodoUpdateDto {

    private String title;
    private String status;
    private Integer order;

    public static Todo buildTodo(Todo todo, TodoUpdateDto todoUpdateDto) {
        return new Todo(
                todo.getId(),
                Optional.ofNullable(todoUpdateDto.getTitle()).orElse(todo.getTitle()),
                Optional.ofNullable(todoUpdateDto.getStatus()).orElseGet(todo::getStatus),
                todoUpdateDto.getOrder()
        );
    }
}
