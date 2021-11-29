package br.com.api.bibliadigital.shared;

import br.com.api.bibliadigital.application.exceptions.BookNotFoundException;
import br.com.api.bibliadigital.application.exceptions.ConfilctLimitRequisitionsIPException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Component
public class BookValidateComponent {

    public void validate(HttpClientErrorException exception) {
        int statusCode = exception.getRawStatusCode();
        String message = exception.getMessage();

        if (statusCode == 404) {
            throw new BookNotFoundException(message);
        }

        if (statusCode == 500) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(message));
        }

        if (statusCode == 409) {
            throw new ConfilctLimitRequisitionsIPException(message);
        }
    }
}
