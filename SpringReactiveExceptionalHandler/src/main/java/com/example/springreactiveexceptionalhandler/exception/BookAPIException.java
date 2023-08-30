package com.example.springreactiveexceptionalhandler.exception;

public class BookAPIException extends Exception{

    public BookAPIException(String message){
        super(message);
    }
}
