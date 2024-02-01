package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoUpdateDto;

import java.util.List;

public interface TodoService {
    TodoResponseDto create(TodoDto todoDto);

    List<TodoResponseDto> findAll();

    TodoResponseDto findOne(Integer id);

    TodoResponseDto udpate(Integer id, TodoUpdateDto todoUpdateDto);

    void delete(Integer id);
}
