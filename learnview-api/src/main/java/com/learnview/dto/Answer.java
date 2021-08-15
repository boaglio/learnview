package com.learnview.dto;

import java.util.List;

import com.learnview.domain.Option;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Answer {

    private String testID;
    private String questionID;

    @EqualsAndHashCode.Exclude
    private List<Option> options;

    @EqualsAndHashCode.Exclude
    private Integer position;

    @EqualsAndHashCode.Exclude
    private Boolean correct;

}
