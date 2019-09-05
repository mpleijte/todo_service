package com.pleijteit.totdo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDtoRequest {
    @Size(min = 0, max = 15, message = "List name has a character limit of 15")
    private String name;
    @Size(min = 0, max = 280, message = "List description has a character limit of 280")
    private String description;
}
