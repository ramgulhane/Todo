package com.todo.dto;

import com.todo.controller.TodoController;
import com.todo.entitiy.Todo;
import lombok.Data;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class TodoDto {

    private Integer id;

    private String title;

    private String status;

    private Integer orderNumber;

    public TodoDto(){

    }
    public TodoDto(String test, String active, int i) {
        this.title= test;
        this.status=active;
        this.orderNumber=i;
    }


    public static Todo buildTodoEntity(TodoDto todoDto) {
            return new Todo(
                    todoDto.getTitle(),
                    todoDto.getStatus(),
                todoDto.getOrderNumber());

    }

}
