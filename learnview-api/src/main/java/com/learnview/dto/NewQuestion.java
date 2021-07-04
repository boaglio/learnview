package com.learnview.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learnview.domain.Option;
import com.learnview.domain.Question;
import com.learnview.domain.Reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class NewQuestion {

    private static final String TRUE = "true";

    @NonNull
    private String id;
    private String message;
    private String category;
    private String subcategory;
    private String exam;
    private String tags;
    private String text;
    private String code;
    private String option1text;
    private String option1answer;
    private String option1correct;
    private String option2text;
    private String option2answer;
    private String option2correct;
    private String option3text;
    private String option3answer;
    private String option3correct;
    private String option4text;
    private String option4answer;
    private String option4correct;
    private String option5text;
    private String option5answer;
    private String option5correct;
    private String explanation;
    private String sourceDescription;
    private String sourceLink;

    public Question getQuestion() {

        Question question = new Question();
        List<Option> options = new ArrayList<>();

        question.setId(id);
        question.setCategory(category);
        question.setSubcategory(subcategory);
        question.setExam(exam);
        String[] tags1 = tags.split(" ");
        String[] tags2 = tags.split(",");
        String[] tags3 = tags.split(";");
        List<String> tagList = new ArrayList<>();
        tagList.addAll(Arrays.asList(tags1));
        tagList.addAll(Arrays.asList(tags2));
        tagList.addAll(Arrays.asList(tags3));
        question.setTags(tagList.stream().distinct().collect(Collectors.toList()));
        question.setCode(code);

        Option option1 = new Option();
        option1.setOption(option1text);
        option1.setAnswer(option1answer);
        if (option1correct != null && option1correct.equalsIgnoreCase(TRUE))
            option1.setCorrect(true);
        options.add(option1);

        Option option2 = new Option();
        option2.setOption(option2text);
        option2.setAnswer(option2answer);
        if (option2correct != null && option2correct.equalsIgnoreCase(TRUE))
            option2.setCorrect(true);
        options.add(option2);

        Option option3 = new Option();
        option3.setOption(option3text);
        option3.setAnswer(option3answer);
        if (option3correct != null && option3correct.equalsIgnoreCase(TRUE))
            option3.setCorrect(true);
        options.add(option3);

        Option option4 = new Option();
        option4.setOption(option4text);
        option4.setAnswer(option4answer);
        if (option4correct != null && option4correct.equalsIgnoreCase(TRUE))
            option4.setCorrect(true);
        options.add(option4);

        Option option5 = new Option();
        option5.setOption(option5text);
        option5.setAnswer(option5answer);
        if (option5correct != null && option5correct.equalsIgnoreCase(TRUE))
            option5.setCorrect(true);
        options.add(option5);

        question.setOptions(options);

        Reference reference = new Reference();
        reference.setDescription(sourceDescription);
        reference.setLink(sourceLink);
        question.setSource(reference);

        return question;
    }

}
