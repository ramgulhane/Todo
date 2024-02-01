package com.todo.service;


import com.todo.dto.TodoDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoUpdateDto;
import com.todo.entitiy.Todo;
import com.todo.excption.TodoOperationException;
import com.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.todo.dto.TodoDto.buildTodoEntity;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoResponseDto create(TodoDto todoDto) {
        Todo todo = todoRepository.save(buildTodoEntity(todoDto));
        return TodoResponseDto.buildTodoResponse(todo);
    }

    @Override
    public List<TodoResponseDto> findAll() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(TodoResponseDto::buildTodoResponse).collect(Collectors.toList());
    }

    @Override
    public TodoResponseDto findOne(Integer id) {
        Optional<Todo> todo= todoRepository.findById(id);

        if(!todo.isPresent()){
            throw new TodoOperationException("Invalid Todo Id, Todo not found!");
        }

        return TodoResponseDto.buildTodoResponse(todo.get());
    }

    @Override
    public TodoResponseDto udpate(Integer id, TodoUpdateDto todoUpdateDto) {
        Optional<Todo> todo= todoRepository.findById(id);

        if(!todo.isPresent()){
            throw new TodoOperationException("Invalid Todo Id, Todo not found!");
        }else{
            Todo updatedTodo = TodoUpdateDto.buildTodo(todo.get(), todoUpdateDto);
            return TodoResponseDto.buildTodoResponse(todoRepository.save(updatedTodo));
        }
    }

    @Override
    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }


}
