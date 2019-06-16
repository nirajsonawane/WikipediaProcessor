package com.niraj.wikipedia.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class WikiFile {

    private Paragraph paragraph;
    private Questions questions;
    private Answers answers;


}
