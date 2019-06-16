package com.niraj.wikipedia.ranking;

import com.niraj.wikipedia.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswerStatementRankingCalculatorTest {

    AnswerStatementRankingCalculator answerStatementRankingCalculator;

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
        stringList.add("XXX TEST ANSWER XXX");
        stringList.add("XXX  ANSWER XXX");

        Paragraph paragraph = Paragraph.builder().statements(stringList).build();
        wikiFile = WikiFile.builder().answers(answers).paragraph(paragraph).build();

        this.answerStatementRankingCalculator =new AnswerStatementRankingCalculator();

    }

    @Test
    public void shouldAssigne_HigestPointsForExactMatch(){

        List<AnswerStatementMatchRanking> answerStatementMatchRankings = answerStatementRankingCalculator.apply(wikiFile);
        String highestRankStatementContainingAnswer = answerStatementMatchRankings
                .get(0)
                .getHighestRankStatementContainingAnswer();
        assertEquals("XXX TEST ANSWER XXX",highestRankStatementContainingAnswer);

    }

}