package com.testproject.test.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "questions")
public class Question {

    @Id
    private UUID id = UUID.randomUUID();

    private String text;

    private QuestionArea questionArea;

    private QuestionType questionType;

    private List<AnswerOption> answerOptions;

}
