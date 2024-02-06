package org.example.exception;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException(){ }
    public NoSuchModelNameException(String Message){
        super(Message);
    }
}
