package com.niraj.wikipedia.util;

import com.niraj.wikipedia.steps.*;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import com.niraj.wikipedia.parser.*;
import com.niraj.wikipedia.ranking.AnswerStatementRankingCalculator;
import com.niraj.wikipedia.ranking.QuestionStatementRankingCalculator;

public class Factory {

    private  WikiFileItemLocation paragraphLocation = new WikiFileItemLocation(Constants.PARAGRAPH_START_INDEX, Constants.PARAGRAPH_TOTAL_LINES);
    private  WikiFileItemLocation questionsLocation = new WikiFileItemLocation(Constants.QUESTION_START_INDEX, Constants.QUESTION_TOTAL_LINES);
    private  WikiFileItemLocation answerLocation = new WikiFileItemLocation(Constants.ANSWER_START_INDEX, Constants.ANSWER_TOTAL_LINES);


    private WikiFileParagraphParser wikiFileParagraphParser = new WikiFileParagraphParser(paragraphLocation);
    private WikiFileQuestionsParser wikiFileQuestionsParser = new WikiFileQuestionsParser(questionsLocation);
    private WikiFileAnswerParser wikiFileAnswerParser = new WikiFileAnswerParser(answerLocation);
    private WikiFileParser wikiFileParser = new WikiFileParser(wikiFileParagraphParser, wikiFileQuestionsParser, wikiFileAnswerParser);


    private AnswerStatementRankingCalculator answerStatementRankingCalculator =new AnswerStatementRankingCalculator();
    private QuestionStatementRankingCalculator questionStatementRankingCalculator = new QuestionStatementRankingCalculator();
    private WikiQuestionAnswerMatcher wikiQuestionAnswerMatcher= new WikiQuestionAnswerMatcher(answerStatementRankingCalculator,questionStatementRankingCalculator);

    private AcceptFileName acceptFileName  = new AcceptFileName();


    private PrintResult printResult = new PrintResult();

    private WikiFileReader wikiFileReader = new WikiFileReader();

    public WikiFileItemLocation getParagraphLocation(){
        return  paragraphLocation;
    }
    public WikiFileItemLocation getQuestionsLocation(){
        return  questionsLocation;
    }
    public WikiFileItemLocation getAnswerLocationn(){
        return  answerLocation;
    }
    public WikiFileParser getWikiFileParser(){
        return  wikiFileParser;
    }
    public WikiFileReader getWikiFileReader(){
        return  wikiFileReader;
    }

    public WikiQuestionAnswerMatcher getWikiQuestionAnswerMatcher() {
         return wikiQuestionAnswerMatcher;
    }
    public PrintResult getPrintResult(){
        return  printResult;
    }
    public AcceptFileName getAcceptFileName(){
        return  acceptFileName;
    }
}
