package br.com.api.bibliadigital.application.handler;

import br.com.api.bibliadigital.application.exceptions.UserAlreadyExistsException;
import br.com.api.bibliadigital.shared.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler extends ValidationsExceptionHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handlerUserAlreadyExistsException(UserAlreadyExistsException exception,
                                                                     WebRequest request) {
        Object[] args = {exception.getMessage()};
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "user.already-exists", args);
    }

    @Override
    protected ResponseEntity<Object> handlerException(Exception exception,
                                                      HttpStatus status,
                                                      WebRequest request,
                                                      String key,
                                                      Object[] args) {
        return super.handlerException(exception, status, request, key, args);
    }
}
