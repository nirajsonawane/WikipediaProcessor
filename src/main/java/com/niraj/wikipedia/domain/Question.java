package com.niraj.wikipedia.domain;

import com.niraj.wikipedia.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Question {
    private String questionKey;
    private String questionString;

    public List<String> getKeyWords(){
        return StringUtil.removeStopWords(questionString);
    }
    public String getQuestion(){
        return  questionKey + " " + questionString  + " ?";
    }
}
