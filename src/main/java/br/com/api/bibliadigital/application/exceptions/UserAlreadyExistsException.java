package br.com.api.bibliadigital.application.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(){}

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
