package br.com.api.bibliadigital.application.exceptions;

public class TokenNotAuthorizedException extends RuntimeException {

    public TokenNotAuthorizedException() {
        super();
    }

    public TokenNotAuthorizedException(String message) {
        super(message);
    }
}
