package com.testproject.test.dtomapping.dto;

import com.testproject.test.domain.AnswerOption;
import com.testproject.test.domain.QuestionArea;
import com.testproject.test.domain.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionOutputDto {
    private String id;

    private String text;

    private QuestionArea questionArea;

    private QuestionType questionType;

    private List<AnswerOption> answerOptions;
}
