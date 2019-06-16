package com.niraj.wikipedia.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Answers {


    private List<Answer> answerList;

}
