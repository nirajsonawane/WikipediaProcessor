package com.niraj.wikipedia.domain;

import com.niraj.wikipedia.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Answer {
    private String answer;



    public List<String> getAnswerKeyWord(){
        return StringUtil.removeStopWords(answer);
    }

}
