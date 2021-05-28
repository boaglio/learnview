package com.learnview.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learnview.dto.Answer;

import lombok.Data;

@Data
@Document("tests")
@JsonInclude(Include.NON_NULL)
public class Test {

    private String        id;
    private String        username;
    private LocalDateTime when;
    private List<Answer>  answers;
    private Integer       total;
    private Integer       correct;
    private Integer       position;

    public Test(String username) {
        this.username = username;
        this.when = LocalDateTime.now();
        this.position = 1;
    }

}
