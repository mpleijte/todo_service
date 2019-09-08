package com.pleijteit.todo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class TodoListKey implements Serializable {

    @Column(name = "list_id")
    Long listId;

    @Column(name = "todo_id")
    Long todoId;

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}
