package de.telran.my_secured_shop.exception_handling.exceptions;

public class EntityValidationException extends RuntimeException{
    public EntityValidationException(String message){
        super(message);
    }
}
