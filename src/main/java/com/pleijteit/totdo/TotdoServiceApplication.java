package com.pleijteit.totdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TotdoServiceApplication {

    public static void main(String[] args) {
        System.out.println("todo");
        SpringApplication.run(TotdoServiceApplication.class, args);
    }

}
