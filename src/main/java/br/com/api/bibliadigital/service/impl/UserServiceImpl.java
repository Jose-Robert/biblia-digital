package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.model.dto.UserV2;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerUsersEndpointApi;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final DigitalBibleConsumerUsersEndpointApi usersEndpoint;
    private final Gson gson;

    public UserServiceImpl(DigitalBibleConsumerUsersEndpointApi usersEndpoint, Gson gson) {
        this.usersEndpoint = usersEndpoint;
        this.gson = gson;
    }

    @Override
    public UserResponseTO createUser(UserRequestTO request) {
        String user = usersEndpoint.postUsers(request);
        return gson.fromJson(user, UserResponseTO.class);
    }

    @Override
    public UserV2 findUser(String email) {
        String user = usersEndpoint.getUser(email);
        return gson.fromJson(user, UserV2.class);
    }
}
