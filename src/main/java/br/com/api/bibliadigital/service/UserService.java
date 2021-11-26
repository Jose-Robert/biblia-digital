package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;

public interface UserService {

    UserResponseTO createUser(UserRequestTO request);
}
