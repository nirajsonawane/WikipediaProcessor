package com.niraj.wikipedia.steps;

import com.niraj.wikipedia.domain.*;
import com.niraj.wikipedia.ranking.AnswerStatementRankingCalculator;
import com.niraj.wikipedia.ranking.QuestionStatementRankingCalculator;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
public class WikiQuestionAnswerMatcher implements Function<WikiFile,OutPut> {

    private AnswerStatementRankingCalculator answerStatementRankingCalculator;
    private QuestionStatementRankingCalculator questionStatementRankingCalculator;


    @Override
    public OutPut apply(WikiFile wikiFile) {

        Map<String, QuestionAnswer> questionAnswerMap = new HashMap<>();
        List<QuestionAnswer> questionAnswerList = new ArrayList<>();
        List<AnswerStatementMatchRanking> answerStatementMatchRankings = answerStatementRankingCalculator.apply(wikiFile);
        List<QuestionStatementMatchRanking> questionStatementMatchRankings = questionStatementRankingCalculator.apply(wikiFile);


        questionStatementMatchRankings.stream().forEach(rank->{
                QuestionAnswer questionAnswer = new QuestionAnswer(rank.getQuestion(),null);
                questionAnswerMap.put(rank.getHighestRankStatementContainingQuestion().trim(),questionAnswer);


        });


        answerStatementMatchRankings.stream().forEach(rank->{
            if(questionAnswerMap.containsKey(rank.getHighestRankStatementContainingAnswer().trim())){
                QuestionAnswer questionAnswer = questionAnswerMap.get(rank.getHighestRankStatementContainingAnswer().trim());
                questionAnswer.setAnswer(rank.getAnswer());
                questionAnswerMap.put(rank.getHighestRankStatementContainingAnswer().trim(),questionAnswer);
            }

        });


        questionAnswerMap.forEach((k,v)->{
          questionAnswerList.add(v);
        });


    return  new OutPut(questionAnswerList);
    }


}
