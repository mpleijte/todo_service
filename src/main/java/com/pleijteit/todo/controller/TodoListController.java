package com.pleijteit.todo.controller;

import com.pleijteit.todo.controller.dto.TodoListDtoRequest;
import com.pleijteit.todo.controller.dto.TodoListDtoResponse;
import com.pleijteit.todo.domain.TodoList;
import com.pleijteit.todo.service.TodoListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@Validated
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/lists")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoListDtoResponse addTodoList(@Valid @RequestBody TodoListDtoRequest newList) {
        return todoListService.createList(newList);
    }

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<@Valid TodoListDtoResponse> getTodoLists() {
        List<TodoListDtoResponse> listOfTodoList = todoListService.getTodoLists();
        return listOfTodoList;
    }

    private TodoListDtoResponse mapToListDto(final TodoList todoList) {
        return new TodoListDtoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getDescription()
        );
    }


}
