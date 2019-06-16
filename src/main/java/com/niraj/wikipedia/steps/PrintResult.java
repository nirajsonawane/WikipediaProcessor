package com.niraj.wikipedia.steps;

import com.niraj.wikipedia.domain.OutPut;

import java.util.function.Function;

public class PrintResult implements Function<OutPut,Void> {




    @Override
    public Void apply(OutPut outPut) {
        System.out.println("Question Answer Matching Result !!!");
        outPut.getQuestionAnswerList().stream().forEach(it->{
            System.out.println("Question  "+ it.getQuestion().getQuestion() + " Answer " + it.getAnswer().getAnswer());
        });
        return null;
    }
}
