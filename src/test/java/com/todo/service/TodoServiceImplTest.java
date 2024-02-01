package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.dto.TodoResponseDto;
import com.todo.dto.TodoUpdateDto;
import com.todo.entitiy.Todo;
import com.todo.excption.TodoOperationException;
import com.todo.repository.TodoRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.todo.dto.TodoDto.buildTodoEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Test
    public void whenICall_Create_ThenShouldCreateTodoItem() {
        TodoDto todoDto = buildTodoDto();

        when(todoRepository.save(buildTodoEntity(todoDto))).thenReturn(new Todo(1, "test", "Active", 123));

        TodoResponseDto todoResponseDto = todoService.create(todoDto);

        assertThat(todoResponseDto.getId(), is(1));
        assertThat(todoResponseDto.getTitle(), is(todoDto.getTitle()));
        assertThat(todoResponseDto.getStatus(), is(todoDto.getStatus()));
        assertThat(todoResponseDto.getOrder(), is(todoDto.getOrderNumber()));
        assertThat(todoResponseDto.getUrl(), is(notNullValue()));

    }

    @Test
    public void whenICall_Find_ThenShouldFindTodoItem() {

        when(todoRepository.findAll()).thenReturn(Lists.newArrayList(new Todo(1, "test", "Active", 123)));
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        TodoResponseDto todoResponseDto = todoResponseDtoList.get(0);
        assertThat(todoResponseDto.getId(), is(1));
        assertThat(todoResponseDto.getTitle(), is("test"));
        assertThat(todoResponseDto.getStatus(), is("Active"));
        assertThat(todoResponseDto.getOrder(), is(123));
    }

    @Test
    public void whenICall_FindOne_ThenShouldFindTodoItem() {

        when(todoRepository.findById(1)).thenReturn(Optional.of(new Todo(1, "test", "Active", 123)));
        TodoResponseDto todoResponseDto= todoService.findOne(1);
        assertThat(todoResponseDto.getId(), is(1));
        assertThat(todoResponseDto.getTitle(), is("test"));
        assertThat(todoResponseDto.getStatus(), is("Active"));
        assertThat(todoResponseDto.getOrder(), is(123));
    }

    @Test
    public void whenICall_Update_ThenShouldUpdateTodoItem() {
        TodoUpdateDto todoUpdateDto= new TodoUpdateDto();
        todoUpdateDto.setTitle("test2");
        todoUpdateDto.setOrder(334);
        Todo todo =new Todo(1,"test2","Active", 334);
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));
        when(todoRepository.save(todo)).thenReturn(todo);
        TodoResponseDto todoResponseDto=todoService.udpate(1, todoUpdateDto);

        assertThat(todoResponseDto.getId(), is(todo.getId()));
        assertThat(todoResponseDto.getTitle(), is( todo.getTitle()));
        assertThat(todoResponseDto.getOrder(), is(todo.getOrderNumber()));
    }

    @Test
    public void whenICall_Update_ThenShouldUpdateThrowException() {
        TodoUpdateDto todoUpdateDto= new TodoUpdateDto();
        todoUpdateDto.setTitle("test2");
        todoUpdateDto.setOrder(334);
        Todo todo =new Todo(1,"test2","Active", 334);
        when(todoRepository.findById(1)).thenReturn(Optional.empty());

        try {
            todoService.udpate(1, todoUpdateDto);
        }catch (TodoOperationException todoOperationException){
            assertThat(todoOperationException.getMessage(), is("Invalid Todo Id, Todo not found!"));
        }

    }


    private static TodoDto buildTodoDto() {
        return new TodoDto("test", "Active", 123);
    }

}
