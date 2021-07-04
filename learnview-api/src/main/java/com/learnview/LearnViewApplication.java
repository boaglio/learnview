package com.learnview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnViewApplication {

    public static final String HTTP_ORIGINS = "http://localhost:8080";

    public static void main(String[] args) {
        SpringApplication.run(LearnViewApplication.class, args);
    }

}
