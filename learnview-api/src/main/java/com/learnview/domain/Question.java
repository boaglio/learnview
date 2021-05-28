package com.learnview.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document("questions")
public class Question extends TestQuestion {

    private List<String> tags;
    private String       explanation;
    private Reference    source;

}
