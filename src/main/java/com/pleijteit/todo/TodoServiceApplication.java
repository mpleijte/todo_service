package com.pleijteit.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoServiceApplication {

    public static void main(String[] args) {
        System.out.println("todo");
        SpringApplication.run(TodoServiceApplication.class, args);
    }

}
