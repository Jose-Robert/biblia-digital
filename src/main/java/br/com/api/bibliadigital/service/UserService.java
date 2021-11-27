package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.model.dto.UserV2;

public interface UserService {

    UserResponseTO createUser(UserRequestTO request);
    UserV2 findUser(String email);
}
