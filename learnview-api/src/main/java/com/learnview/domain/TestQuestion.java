package com.learnview.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(Include.NON_NULL)
public class TestQuestion {

    @Id
    private String id;
    private String category;
    private String subcategory;
    private String exam;
    private String text;
    private String code;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Option> options;

    private int optSize;

    // boolean correct(List<Option> userOptions) {
    //
    // Boolean[] allAnswersCorrect = { true };
    //
    // final Map<String, Option> optionMap = this.options.stream()
    // .collect(Collectors.toMap(Option::getOption, Function.identity()));
    //
    // this.options.forEach(opt -> {
    // Option rightOption = optionMap.get(opt.getOption());
    // if (!opt.getSelected() == rightOption.getCorrect()) {
    // allAnswersCorrect[0] = false;
    // }
    // });
    //
    // return allAnswersCorrect[0];
    // }
}
