package com.niraj.wikipedia.ranking;

import com.niraj.wikipedia.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionStatementRankingCalculatorTest {

    QuestionStatementRankingCalculator questionStatementRankingCalculator;

    WikiFile wikiFile;

    @BeforeEach
    public void setup(){

        Answer answer = new Answer("TEST ANSWER");
        List<Answer> answerList = new ArrayList<>();
        answerList.add(answer);
        Answers answers = Answers
                .builder()
                .answerList(answerList)
                .build();
        List<String> stringList = new ArrayList<>();
        stringList.add("SOME RANDOME TEST");
        stringList.add("GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA ARE ENDANGERED");
        stringList.add("ARE");

        Question question  = new Question("WHICH", "ZEBRAS ARE ENDANGERED");
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);


        Questions questions = Questions
                .builder()
                .questionList(questionList)
                .build();

        Paragraph paragraph = Paragraph.builder().statements(stringList).build();
        wikiFile = WikiFile.builder().answers(answers).questions(questions).paragraph(paragraph).build();

        this.questionStatementRankingCalculator =new QuestionStatementRankingCalculator();

    }

    @Test
    public void shouldAssigne_HigestPointsForExactMatch(){

        List<QuestionStatementMatchRanking> questionStatementMatchRankings = questionStatementRankingCalculator.apply(wikiFile);
        String highestRankStatementContainingAnswer = questionStatementMatchRankings
                .get(0)
                .getHighestRankStatementContainingQuestion();
        assertEquals("GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA ARE ENDANGERED",highestRankStatementContainingAnswer);

    }

}