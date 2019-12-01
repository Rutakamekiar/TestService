package com.testproject.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerOption {

    private String answer;
    private boolean correct;
}
