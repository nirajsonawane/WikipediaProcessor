package com.niraj.wikipedia.domain;

import lombok.Getter;

public enum QuestionKeyMapEnum {

    WHAT_IS("WHAT IS"),
    WHAT("WHAT"),
    WHICH_ARE("WHICH ARE"),
    WHICH("WHICH");
    @Getter
    String valueString;

    QuestionKeyMapEnum(String value) {
        this.valueString = value;
    }


}
