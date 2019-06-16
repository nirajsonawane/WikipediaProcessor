package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.Answers;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WikiFileAnswerParserTest {

    private WikiFileAnswerParser wikiFileAnswerParser;

    List<String> lines ;


    @BeforeEach
    public void setup(){
        lines= new ArrayList<>();
        lines.add("Line 1");
        lines.add("subgenus Hippotigris; the plains zebra, the Grévy's zebra and the mountain zebra ; horses and donkeys ;aims to breed zebras that are phenotypically similar to the quagga ; Grévy's zebra and the mountain zebra");
        lines.add("Line 3");
    }






    @Test
    public  void shouldParseSingleLineAnswersCorrectly_ShouldConvertTextToUpperCase_ShouldDoTrim_ShouldDoStaming(){
        wikiFileAnswerParser= new WikiFileAnswerParser(new WikiFileItemLocation(1,1));
        Answers answers = wikiFileAnswerParser.apply(lines.stream());
        Assert.assertNotNull(answers);
        Assert.assertNotNull(answers.getAnswerList());
        Assert.assertEquals(answers.getAnswerList().size(),5);
        Assert.assertEquals("SUBGENUS HIPPOTIGRIS",answers.getAnswerList().get(0).getAnswer());
        Assert.assertEquals("THE PLAINS ZEBRA, THE GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA",answers.getAnswerList().get(1).getAnswer());      ;
        Assert.assertEquals("HORSES AND DONKEYS",answers.getAnswerList().get(2).getAnswer());
        Assert.assertEquals("AIMS TO BREED ZEBRA THAT ARE PHENOTYPICALLY SIMILAR TO THE QUAGGA",answers.getAnswerList().get(3).getAnswer());
        Assert.assertEquals("GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA",answers.getAnswerList().get(4).getAnswer());


    }
    @Test
    public  void shouldParse_MultiLineAnswersCorrectly_ShouldConvertTextToUpperCase(){
        wikiFileAnswerParser= new WikiFileAnswerParser(new WikiFileItemLocation(1,2));
        Answers answers = wikiFileAnswerParser.apply(lines.stream());
        Assert.assertNotNull(answers);
        Assert.assertNotNull(answers.getAnswerList());
        Assert.assertEquals(answers.getAnswerList().size(),6);
        Assert.assertEquals("SUBGENUS HIPPOTIGRIS",answers.getAnswerList().get(0).getAnswer());
        Assert.assertEquals("THE PLAINS ZEBRA, THE GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA",answers.getAnswerList().get(1).getAnswer());      ;
        Assert.assertEquals("HORSES AND DONKEYS",answers.getAnswerList().get(2).getAnswer());
        Assert.assertEquals("AIMS TO BREED ZEBRA THAT ARE PHENOTYPICALLY SIMILAR TO THE QUAGGA",answers.getAnswerList().get(3).getAnswer());
        Assert.assertEquals("GRÉVY'S ZEBRA AND THE MOUNTAIN ZEBRA",answers.getAnswerList().get(4).getAnswer());
        Assert.assertEquals("LINE 3",answers.getAnswerList().get(5).getAnswer());

    }

}