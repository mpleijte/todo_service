package com.pleijteit.todo.service;

import com.pleijteit.todo.controller.dto.TodoListDtoRequest;
import com.pleijteit.todo.controller.dto.TodoListDtoResponse;
import com.pleijteit.todo.domain.TodoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Slf4j
@Service
public class TodoListService {

    private List<TodoList> todoList = new ArrayList<TodoList>();

    public List<TodoListDtoResponse> getTodoLists() {
        initTodoList();

        return todoList == null ? emptyList() : todoList.stream()
                .map(todoList -> mapToListDto(todoList))
                .collect(Collectors.toList());
    }

    public TodoListDtoResponse createList(final TodoListDtoRequest todoListDto) {
        final TodoList list = new TodoList(UUID.randomUUID().toString(), todoListDto.getName(), todoListDto.getDescription());
        todoList.add(list);
        TodoListDtoResponse response = new TodoListDtoResponse();
        response.setId(list.getId());
        return response;
    }

    private void initTodoList() {
        TodoList newList1 = new TodoList("1be0a200-4e91-4e72-b61d-6ddbeae0d8c7","rememberTheMilk","shopping list");
        TodoList newList2 = new TodoList("0f56aa0d-cd91-43d2-9c08-a0841b904c03","vacation","packing list");
        TodoList newList3 = new TodoList("801c4823-f012-4cd2-b619-a632937dcbd1","birthday sister","shopping list for birthday sister");
        todoList.add(newList1);
        todoList.add(newList2);
        todoList.add(newList3);
    }

    private TodoListDtoResponse mapToListDto(final TodoList todoList) {
        return new TodoListDtoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getDescription()
        );
    }

}
