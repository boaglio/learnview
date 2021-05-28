package com.learnview.domain;

import com.learnview.dto.OptionAnswer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Option extends OptionAnswer {

    private String  answer;
    private boolean correct;

}
