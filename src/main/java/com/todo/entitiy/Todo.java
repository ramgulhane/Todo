package com.todo.entitiy;

import com.todo.controller.TodoController;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Entity
@Data
@Table(name="todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "order_number")
    private Integer orderNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createdTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    public Todo(){
    }
    public Todo(Integer id, String title, String status, Integer orderNumber) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.orderNumber = orderNumber;
    }

    public Todo(String title, String status, Integer orderNumber) {
        this.title = title;
        this.status = status;
        this.orderNumber = orderNumber;
    }

    public String getUrl() {
        return linkTo(TodoController.class).slash(this.getId()).withSelfRel().getHref();
    }

}
