package com.niraj.wikipedia;

import com.niraj.wikipedia.steps.WikiFileParser;
import com.niraj.wikipedia.steps.WikiQuestionAnswerMatcher;
import com.niraj.wikipedia.steps.AcceptFileName;
import com.niraj.wikipedia.steps.PrintResult;
import com.niraj.wikipedia.util.Factory;
import com.niraj.wikipedia.steps.WikiFileReader;

public class Main {


    public static void main(String[] args) {




        Factory factory = new Factory();
        AcceptFileName acceptFileName = factory.getAcceptFileName();
        String fileName = acceptFileName.get();
        WikiFileParser wikiFileParser = factory.getWikiFileParser();
        WikiFileReader wikiFileReader = factory.getWikiFileReader();
        WikiQuestionAnswerMatcher wikiQuestionAnswerMatcher = factory.getWikiQuestionAnswerMatcher();
        PrintResult printResult = factory.getPrintResult();
        StartProcessing(fileName, wikiFileParser, wikiFileReader, wikiQuestionAnswerMatcher, printResult);

    }

    public static Void StartProcessing(String fileName, WikiFileParser wikiFileParser, WikiFileReader wikiFileReader, WikiQuestionAnswerMatcher wikiQuestionAnswerMatcher, PrintResult printResult) {
        return wikiFileReader
                .andThen(wikiFileParser)
                .andThen(wikiQuestionAnswerMatcher)
                .andThen(printResult)
                .apply(fileName);
    }
}
