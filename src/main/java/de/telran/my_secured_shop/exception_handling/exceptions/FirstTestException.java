package de.telran.my_secured_shop.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FirstTestException extends RuntimeException{
    public FirstTestException(String message){super(message);}
}
