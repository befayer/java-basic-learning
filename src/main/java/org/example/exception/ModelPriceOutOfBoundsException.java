package org.example.exception;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(){}
    public ModelPriceOutOfBoundsException(String Message){
        super(Message);
    }
}
