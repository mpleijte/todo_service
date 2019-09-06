package com.pleijteit.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoList {
    private String id;
    private String name;
    private String description;
}
