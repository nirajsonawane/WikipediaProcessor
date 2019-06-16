package com.niraj.wikipedia.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    private final static String STOP_WORDS = "THE|AND|TO|BUT|THAT|ARE|ZEBRA";

    public static List<String> removeStopWords(String inputString) {

        String s = inputString.replaceAll("(" + STOP_WORDS + ")\\s*", "");
        return Arrays.asList(s.split(" ")).stream().collect(Collectors.toList());
    }
    public static Integer getStringMatchingRanking(List<String> answerKeyWord, String statement) {
        int rannking =0;
        for(String keyWord:answerKeyWord){
            if(statement.contains(keyWord)){
                rannking=rannking+1;
            }
        }
        return  rannking;
    }
}
