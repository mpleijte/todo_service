package com.pleijteit.todo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class TodoList {

    @EmbeddedId
    TodoListKey id;

    @ManyToOne
    @MapsId("list_id")
    @JoinColumn(name = "list_id")
    List list;

    @ManyToOne
    @MapsId("todo_id")
    @JoinColumn(name = "todo_id")
    Todo todo;

    int prio;
}
