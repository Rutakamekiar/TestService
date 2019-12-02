package com.testproject.test.domain;

import lombok.Data;

@Data
class UserAnswer {
    private Question question;
    private boolean correct;
}
