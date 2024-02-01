package com.todo.dto;

import com.todo.entitiy.Todo;
import lombok.Data;

@Data
public class TodoResponseDto {

    private Integer id;
    private String title;
    private String status;
    private Integer order;
    private String url;

    public TodoResponseDto(Integer id, String title, String status, Integer orderNumber, String url) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.order = orderNumber;
        this.url = url;
    }


    public static TodoResponseDto buildTodoResponse(Todo todo) {
        return new TodoResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getStatus(),
                todo.getOrderNumber(),
                todo.getUrl());
    }

}
