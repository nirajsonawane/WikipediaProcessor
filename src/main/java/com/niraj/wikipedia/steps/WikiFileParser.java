package com.niraj.wikipedia.steps;

import com.niraj.wikipedia.domain.Answers;
import com.niraj.wikipedia.domain.Paragraph;
import com.niraj.wikipedia.domain.Questions;
import com.niraj.wikipedia.domain.WikiFile;
import com.niraj.wikipedia.parser.WikiFileAnswerParser;
import com.niraj.wikipedia.parser.WikiFileParagraphParser;
import com.niraj.wikipedia.parser.WikiFileQuestionsParser;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class WikiFileParser implements Function<List<String>, WikiFile> {

    private final WikiFileParagraphParser wikiFileParagraphParser;
    private final WikiFileQuestionsParser wikiFileQuestionsParser;
    private final WikiFileAnswerParser wikiFileAnswerParser;

    @Override
    public WikiFile apply(List<String> lines) {


        Paragraph paragraph = wikiFileParagraphParser.apply(lines.stream());
        Answers answers = wikiFileAnswerParser.apply(lines.stream());
        Questions questions = wikiFileQuestionsParser.apply(lines.stream());
        return WikiFile
                .builder()
                .paragraph(paragraph)
                .answers(answers)
                .questions(questions)
                .build();


    }
}
