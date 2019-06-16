package com.niraj.wikipedia.domain;

import com.niraj.wikipedia.exception.WikiFileProcessorException;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


@ToString
public class QuestionStatementMatchRanking {

    @Getter
    private  Question question;
    @Getter
    private Map<String,Integer> statementRankingMap = new HashMap<>();


    public QuestionStatementMatchRanking(Question question){
        this.question=question;
    }

    public  void addStatementRanking(String statement,Integer ranking){
        if(statementRankingMap.containsKey(statement)){
            Integer integer = statementRankingMap.get(statement);
            statementRankingMap.put(statement,integer+ranking);
        }
        else {
            statementRankingMap.put(statement,ranking);
        }
    }
    public Map<String,Integer> getStatementRankingMapOrderByBestMatch(){
        return statementRankingMap.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
    public String getHighestRankStatementContainingQuestion(){
        return getStatementRankingMapOrderByBestMatch().keySet().stream().findFirst().orElseThrow(()-> new WikiFileProcessorException("Unable to find Statement , that might contian the Question"));
    }

}
