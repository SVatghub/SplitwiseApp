package com.sahil.SplitwiseApp.exceptions;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException(String msg){
        super(msg);
    }
}
