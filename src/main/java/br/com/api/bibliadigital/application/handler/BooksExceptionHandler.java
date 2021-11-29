package br.com.api.bibliadigital.application.handler;

import br.com.api.bibliadigital.application.exceptions.BookNotFoundException;
import br.com.api.bibliadigital.shared.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BooksExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<Object> handlerBookNotFoundException(BookNotFoundException exception,
                                                               WebRequest request) {
        Object[] args = {exception.getMessage()};
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "book.not-found", args);
    }

    private ResponseEntity<Object> handlerException(Exception exception,
                                                    HttpStatus status,
                                                    WebRequest request,
                                                    String key,
                                                    Object[] args) {
        ApiError<List<String>> response = new ApiError<>(List.of((messageService.getMessage(key, args))));
        response.setStatusCode(status.value());
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }
}
