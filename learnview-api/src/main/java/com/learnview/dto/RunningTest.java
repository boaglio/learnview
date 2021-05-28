package com.learnview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learnview.domain.Test;
import com.learnview.domain.TestQuestion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class RunningTest extends AbstractResponse {

    private TestQuestion question;

    private Test test;

}
