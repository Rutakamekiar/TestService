package com.testproject.test.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "tests")
public class Test {

    @Id
    private UUID id;

    private String userId;

    private List<UserAnswer> testQuestions;
}
