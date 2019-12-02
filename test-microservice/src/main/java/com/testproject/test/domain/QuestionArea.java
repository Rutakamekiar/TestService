package com.testproject.test.domain;

import java.util.Arrays;

public enum QuestionArea {
    JAVA("java"), C_SHARP("c-sharp"), C_PLUS_PLUS("cpp");

    private String value;

    QuestionArea(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static QuestionArea getAreaFromValue(String value) {
        return Arrays.stream(QuestionArea.values())
                .filter(area -> area.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
