package com.pleijteit.totdo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TodoList {
    private String id;
    private String name;
    private String description;
}
