package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.WikiFile;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import com.niraj.wikipedia.steps.WikiFileParser;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WikiFileParserTest {

    private WikiFileParser wikiFileParser;
    List<String> lines ;


    @BeforeEach
    public void setup(){
        lines= new ArrayList<>();
        lines.add("Line 1");
        lines.add("What Line 2");
        lines.add("Line 3");
    }

    @Test
    public void shouldCreateWikiFileObjectByDelegatingItToOtherParsers(){
        WikiFileItemLocation paragraphLocation = new WikiFileItemLocation(0, 1);
        WikiFileItemLocation questionsLocation = new WikiFileItemLocation(1, 1);
        WikiFileItemLocation answerLocation = new WikiFileItemLocation(2, 1);

        WikiFileParagraphParser wikiFileParagraphParser = new WikiFileParagraphParser(paragraphLocation);
        WikiFileQuestionsParser wikiFileQuestionsParser = new WikiFileQuestionsParser(questionsLocation);
        WikiFileAnswerParser wikiFileAnswerParser = new WikiFileAnswerParser(answerLocation);
        wikiFileParser =new WikiFileParser(wikiFileParagraphParser,wikiFileQuestionsParser,wikiFileAnswerParser);
        WikiFile wikiFile = wikiFileParser.apply(lines);
        Assert.assertNotNull(wikiFile);
        Assert.assertNotNull(wikiFile.getParagraph());
        Assert.assertNotNull(wikiFile.getAnswers());
        Assert.assertNotNull(wikiFile.getQuestions());

    }

}