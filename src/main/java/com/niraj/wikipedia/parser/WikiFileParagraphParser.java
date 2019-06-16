package com.niraj.wikipedia.parser;

import com.niraj.wikipedia.domain.Paragraph;
import com.niraj.wikipedia.domain.WikiFileItemLocation;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class WikiFileParagraphParser implements Function<Stream<String> ,Paragraph> {

    private WikiFileItemLocation wikiFileItemLocation;


    @Override
    public Paragraph apply(Stream<String> stringStream) {
        List<String> paragrapthLines = stringStream
                .skip(wikiFileItemLocation.getStartLine())
                .limit(wikiFileItemLocation.getTotalLines())
                .flatMap(it->Stream.of(it.split("\\.")))
                .map(String::toUpperCase)
                .map(str->str.replace("ZEBRAS","ZEBRA"))
                .collect(Collectors.toList());
        return  Paragraph
                .builder()
                .statements(paragrapthLines)
                .build();

    }
}
