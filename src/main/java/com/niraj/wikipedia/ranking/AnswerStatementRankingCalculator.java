package com.niraj.wikipedia.ranking;

import com.niraj.wikipedia.domain.Answer;
import com.niraj.wikipedia.domain.AnswerStatementMatchRanking;
import com.niraj.wikipedia.domain.WikiFile;
import com.niraj.wikipedia.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AnswerStatementRankingCalculator implements Function<WikiFile, List<AnswerStatementMatchRanking>> {
    @Override
    public List<AnswerStatementMatchRanking> apply(WikiFile wikiFile) {
        List<Answer> answerList = wikiFile
                .getAnswers()
                .getAnswerList();

        List<String> statements = wikiFile
                .getParagraph()
                .getStatements();
        List<AnswerStatementMatchRanking> answerStatementMatchRankingList = new ArrayList<>();

        for (Answer answer : answerList) {
            AnswerStatementMatchRanking answerStatementMatchRanking = new AnswerStatementMatchRanking(answer);
            for (String st : statements) {
                answerStatementMatchRanking.addStatementRanking(st, 0);
                if (st.contains(answer.getAnswer().trim())) {
                    int rank= 1000;

                    String difference = StringUtils.difference(answer
                            .getAnswer()
                            .trim(),st);

                    answerStatementMatchRanking.addStatementRanking(st, rank-difference.length());
                }
                Integer ranking = StringUtil.getStringMatchingRanking(answer.getAnswerKeyWord(), st);
                answerStatementMatchRanking.addStatementRanking(st, ranking);
            }
            answerStatementMatchRankingList.add(answerStatementMatchRanking);
        }
        return answerStatementMatchRankingList;
    }
}
