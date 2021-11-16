package com.learnview.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learnview.domain.TestQuestion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class TestResults extends AbstractResponse {

    private String        testID;
    private String        username;
    private String        exam;
    private LocalDateTime when;
    private Integer       correct;
    private Integer       total;
    private Integer       position;

    private TestQuestion question;

    private Answer answer;

}
