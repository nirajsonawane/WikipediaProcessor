package com.niraj.wikipedia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QuestionAnswer {
    private Question question;
    private  Answer answer;
}
