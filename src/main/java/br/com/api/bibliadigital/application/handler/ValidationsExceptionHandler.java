package br.com.api.bibliadigital.application.handler;

import br.com.api.bibliadigital.application.exceptions.ConfilctLimitRequisitionsIPException;
import br.com.api.bibliadigital.shared.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationsExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageService messageService;

    @ExceptionHandler({ConfilctLimitRequisitionsIPException.class})
    public ResponseEntity<Object> handlerConfilctLimitRequisitionsIPException(ConfilctLimitRequisitionsIPException exception,
                                                               WebRequest request) {
        Object[] args = {exception.getMessage()};
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "general.limit-requests-ip", args);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handlerHttpClientErrorException(HttpClientErrorException exception,
                                                                  WebRequest request) {
        Object[] args = {exception.getMessage()};
        return handlerException(exception, HttpStatus.BAD_REQUEST, request, "general.internal-server-error", args);
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
