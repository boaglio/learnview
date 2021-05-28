package com.learnview.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class TestQuestion {

    @Id
    private String       id;
    private String       category;
    private String       subcategory;
    private String       exam;
    private String       text;
    private String       code;
    private List<Option> options;

}
