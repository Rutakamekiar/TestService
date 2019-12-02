package com.testproject.test.dtomapping.dto;

import com.testproject.test.domain.AnswerOption;
import com.testproject.test.domain.QuestionArea;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionInputDto {
    @NotBlank
    private String text;
    @NotBlank
    private QuestionArea questionArea;
    @NotEmpty
    private List<AnswerOption> answerOptions = new ArrayList<>();
}
