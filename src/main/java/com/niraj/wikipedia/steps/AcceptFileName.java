package com.niraj.wikipedia.steps;

import com.niraj.wikipedia.exception.WikiFileProcessorException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class AcceptFileName implements Supplier<String> {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String get() {
        System.out.println("Please Enter Absolute  File Path : ");
        try {
            return  in.readLine();
        } catch (IOException e) {
            throw new WikiFileProcessorException("Error while reading input");
        }
    }
    public void mockInput(ByteArrayInputStream mock) {
        this.in = new BufferedReader(new InputStreamReader(mock));
    }


}
