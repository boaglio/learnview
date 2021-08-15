package com.learnview.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@JsonInclude(Include.NON_NULL)
public class Option {

    private String  option;
    private Boolean selected;

    @EqualsAndHashCode.Exclude
    private Boolean correct;

    @EqualsAndHashCode.Exclude
    private String answer;

}
