package br.com.api.bibliadigital.shared;

import br.com.api.bibliadigital.application.exceptions.UserAlreadyExistsException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Primary
@Component
public class UserValidateComponent extends ValidateComponent {

    @Override
    public void validate(HttpClientErrorException exception) {
        int statusCode = exception.getRawStatusCode();
        String message = exception.getMessage();

        if (statusCode == 400) {
            throw new UserAlreadyExistsException(message);
        }
        super.validate(exception);
    }
}
