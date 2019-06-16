package com.niraj.wikipedia.steps;

import com.niraj.wikipedia.exception.WikiFileProcessorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WikiFileReader implements Function<String,List<String>> {
    @Override
    public List<String> apply(String fileName) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get((fileName)));
            return  lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new WikiFileProcessorException("Error While Parsing File ", e);
        }

    }
}
