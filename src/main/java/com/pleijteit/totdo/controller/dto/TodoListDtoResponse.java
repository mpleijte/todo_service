package com.pleijteit.totdo.controller.dto;

import com.pleijteit.totdo.util.UuidUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDtoResponse {
    @Pattern(regexp = UuidUtil.UUID_PATTERN, message = "id format error, UUID format expected")
    private String id;
    @Size(min = 0, max = 15, message = "List name has a character limit of 15")
    private String name;
    @Size(min = 0, max = 280, message = "List description has a character limit of 280")
    private String description;
}
