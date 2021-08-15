package com.learnview.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learnview.dto.Answer;

import lombok.Data;
import lombok.ToString;

@Data
@Document("tests")
@ToString
@JsonInclude(Include.NON_NULL)
public class Test {

    private String        id;
    private String        username;
    private String        exam;
    private LocalDateTime when;
    private List<Answer>  answers;
    private Integer       correct;
    private int           total;
    private int           position;
    private List<Integer> order;
    private boolean       completed;

    public Test(String username) {
        this.username = username;
        this.when = LocalDateTime.now();
        this.position = 1;
        this.answers = new ArrayList<Answer>();
    }

}
