package com.niraj.wikipedia.exception;

public class WikiFileProcessorException extends  RuntimeException {

    public WikiFileProcessorException(String message,Throwable t){
        super(message,t);
    }
    public WikiFileProcessorException(Throwable t){
        super(t);
    }
    public WikiFileProcessorException(String message){
        super(message);
    }
}
