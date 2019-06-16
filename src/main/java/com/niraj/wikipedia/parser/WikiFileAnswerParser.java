package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.Answer;
import com.niraj.wikipedia.domain.Answers;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class WikiFileAnswerParser implements Function<Stream<String>, Answers> {

    private WikiFileItemLocation wikiFileItemLocation;


    @Override
    public Answers apply(Stream<String> stringStream) {

        List<Answer> answerList = stringStream
                .skip(wikiFileItemLocation.getStartLine())
                .limit(wikiFileItemLocation.getTotalLines())
                .flatMap(it -> Stream.of(it.split(";")))
                .map(String::toUpperCase)
                .map(String::trim)
                .map(str->str.replace("ZEBRAS","ZEBRA"))
                .map(it -> new Answer(it))
                .collect(Collectors.toList());

        return Answers
                .builder()
                .answerList(answerList)
                .build();


    }
}
