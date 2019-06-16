package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.Question;
import com.niraj.wikipedia.domain.QuestionKeyMapEnum;
import com.niraj.wikipedia.domain.Questions;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import com.niraj.wikipedia.exception.WikiFileProcessorException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class WikiFileQuestionsParser implements Function<Stream<String>, Questions> {

    private WikiFileItemLocation wikiFileItemLocation;


    @Override
    public Questions apply(Stream<String> stringStream) {
        List<Question> questionList = stringStream
                .skip(wikiFileItemLocation.getStartLine())
                .limit(wikiFileItemLocation.getTotalLines())
                .map(String::toUpperCase)
                .map(String::trim)
                .map(str->str.replace("ZEBRAS","ZEBRA"))
                .map(it -> mapToQuestion(it))

                .collect(Collectors.toList());
        return Questions
                .builder()
                .questionList(questionList)
                .build();

    }

    private boolean isSubString(String source, String stringToSearch) {
        return Pattern
                .compile(Pattern.quote(stringToSearch), Pattern.CASE_INSENSITIVE)
                .matcher(source)
                .find();
    }

    private Question mapToQuestion(String questionString) {

        return Stream
                .of(QuestionKeyMapEnum.values())
                .filter(it -> isSubString(questionString, it.getValueString()))
                .findFirst()
                .map(it -> new Question(it.getValueString(), questionString.replace(it.getValueString(), "").replace("?","")))
                .orElseThrow(() -> new WikiFileProcessorException("Question is not in correctFormat or Format is not yet supported"));


    }
}
