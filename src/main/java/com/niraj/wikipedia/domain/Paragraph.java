package com.niraj.wikipedia.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Paragraph {

    private List<String> statements;

    public int getTotoalNumberOfParagraph(){
        return statements.size();
    }


}
