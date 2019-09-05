package com.pleijteit.totdo.controller;

import com.pleijteit.totdo.controller.dto.TodoListDtoRequest;
import com.pleijteit.totdo.controller.dto.TodoListDtoResponse;
import com.pleijteit.totdo.domain.TodoList;
import com.pleijteit.totdo.service.TodoListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

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
    public TodoListDtoResponse addTodoList(@RequestBody TodoListDtoRequest newList) {
        String uuid = todoListService.createList(newList);
        TodoListDtoResponse response= new TodoListDtoResponse();
        response.setId(uuid);
        return response;
    }

    @GetMapping("/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<@Valid TodoListDtoResponse> getTodoLists() {
        List<TodoList> todoLists = todoListService.getTodoLists();
        return todoLists == null ? emptyList() : todoLists
                .stream()
                .map(todoList -> mapToListDto(todoList))
                .collect(Collectors.toList());
//        TodoListDtoResponse response = new TodoListDtoResponse();
//        response.setId("478b7104-5de0-458c-a33a-de134ccd608f");
//        response.setName("aListName");
//        response.setDescription("aListDescription");
//        List<TodoListDtoResponse> responseList = new ArrayList<TodoListDtoResponse>();
//        responseList.add(response);
//        return responseList;
    }

    private TodoListDtoResponse mapToListDto(final TodoList todoList) {
        return new TodoListDtoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getDescription()
        );
    }

//    private TodoList createToDoList(final TodoListDtoRequest todoListDtoRequest) {
//        TodoList todoList = new TodoList();
//        todoList.setName(todoListDtoRequest.getName());
//        todoList.setDescription(todoListDtoRequest.getDescription());
//        return todoList;
//    }

}
