package com.niraj.wikipedia.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OutPut {
    List<QuestionAnswer> questionAnswerList ;
}
