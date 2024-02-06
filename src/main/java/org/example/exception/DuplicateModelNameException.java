package org.example.exception;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(){ }
    public DuplicateModelNameException(String Message){
        super(Message);
    }
}
