package com.learnview.dto;

import java.util.List;

import lombok.Data;

@Data
public class Answer {

    private String             testID;
    private String             questionID;
    private List<OptionAnswer> options;
    private Boolean            mark;
    private Integer            position;

}
