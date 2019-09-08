package com.pleijteit.todo.dao;

import javax.persistence.*;

@Entity
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private String description;
}
