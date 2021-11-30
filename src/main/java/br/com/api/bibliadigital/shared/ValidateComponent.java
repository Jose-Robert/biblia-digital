package br.com.api.bibliadigital.shared;

import br.com.api.bibliadigital.application.exceptions.ResourceNotFoundException;
import br.com.api.bibliadigital.application.exceptions.TokenNotAuthorizedException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Primary
@Component
public class ValidateComponent {

    public void validate(HttpClientErrorException exception) {
        int statusCode = exception.getRawStatusCode();
        String message = exception.getMessage();

        if (statusCode == 403) {
            throw new TokenNotAuthorizedException(message);
        }

        if (statusCode == 404) {
            throw new ResourceNotFoundException(message);
        }

    }
}
