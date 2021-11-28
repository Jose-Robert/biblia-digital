package br.com.api.bibliadigital.service;

import br.com.api.bibliadigital.model.Stats;
import br.com.api.bibliadigital.model.dto.*;

public interface UserService {

    UserResponseTO createUser(UserRequestTO requestTO);
    UserV2 findUser(String email);
    Stats findAllStatistics();
    TokenResponseTO updateToken(TokenRequestTO requestTO);
}
