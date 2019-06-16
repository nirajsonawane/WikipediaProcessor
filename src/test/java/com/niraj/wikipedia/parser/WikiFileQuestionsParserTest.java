package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.Questions;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WikiFileQuestionsParserTest {

    List<String> lines ;
    private WikiFileQuestionsParser wikiFileQuestionsParser;

    @BeforeEach
    public void setup(){
        lines= new ArrayList<>();
        lines.add("Line 1");
        lines.add("Line 2");
        lines.add("Which Zebras are endangered?");
        lines.add("What is the aim of the Quagga Project?");
        lines.add("Which animals are some of their closest relatives?");
        lines.add("Which are the three species of zebras?");
        lines.add("Which subgenus do the plains zebra and the mountain zebra belong to?");
        lines.add("Line 8");
    }

    @Test
    public void shouldParseQuestion_SingleQuestion_ShouldConvertToUpeerCase_ShouldTrim_ShouldDoStaming(){
        wikiFileQuestionsParser = new WikiFileQuestionsParser(new WikiFileItemLocation(2,5));
        Questions questions = wikiFileQuestionsParser.apply(lines.stream());
        Assert.assertNotNull(questions);
        Assert.assertEquals("WHICH",questions.getQuestionList().get(0).getQuestionKey());
        Assert.assertEquals("ZEBRA ARE ENDANGERED",questions.getQuestionList().get(0).getQuestionString().trim());
        Assert.assertEquals("WHICH  ZEBRA ARE ENDANGERED ?",questions.getQuestionList().get(0).getQuestion());
    }
    @Test
    public void shouldParseQuestion_ShouldConvertToUpeerCase_ShouldTrim_ShouldDoStaming(){
        wikiFileQuestionsParser = new WikiFileQuestionsParser(new WikiFileItemLocation(2,5));
        Questions questions = wikiFileQuestionsParser.apply(lines.stream());
        Assert.assertNotNull(questions);
        Assert.assertEquals(5,questions.getQuestionList().size());

    }
}