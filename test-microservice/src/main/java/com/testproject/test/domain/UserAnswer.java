package com.testproject.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class UserAnswer {

    private Question question;

    private boolean correct;
}
