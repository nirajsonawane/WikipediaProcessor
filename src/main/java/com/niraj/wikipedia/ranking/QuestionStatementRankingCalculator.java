package com.niraj.wikipedia.ranking;

import com.niraj.wikipedia.domain.Question;
import com.niraj.wikipedia.domain.QuestionStatementMatchRanking;
import com.niraj.wikipedia.domain.WikiFile;
import com.niraj.wikipedia.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QuestionStatementRankingCalculator implements Function<WikiFile,List<QuestionStatementMatchRanking>> {
    @Override
    public List<QuestionStatementMatchRanking> apply(WikiFile wikiFile) {

        List<Question> questionList = wikiFile.getQuestions().getQuestionList();
        List<String> statements = wikiFile.getParagraph().getStatements();

        List<QuestionStatementMatchRanking> questionStatementMatchRankings = new ArrayList<>();
        for(Question question:questionList){
            QuestionStatementMatchRanking questionStatementMatchRanking = new QuestionStatementMatchRanking(question);
            for (String st:statements){
                questionStatementMatchRanking.addStatementRanking(st,0);
                if(st.contains(question.getQuestionString().trim())){
                    questionStatementMatchRanking.addStatementRanking(st,10);
                }
                Integer ranking = StringUtil.getStringMatchingRanking(question.getKeyWords(), st);
                questionStatementMatchRanking.addStatementRanking(st,ranking);
            }
            questionStatementMatchRankings.add(questionStatementMatchRanking);
        }
        return questionStatementMatchRankings;
    }
}
