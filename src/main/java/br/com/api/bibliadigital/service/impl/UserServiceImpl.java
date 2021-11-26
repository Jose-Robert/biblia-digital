package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.service.integration.DigitalBibleConsumerUsersEndpointApi;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DigitalBibleConsumerUsersEndpointApi usersEndpoint;

    @Override
    public UserResponseTO createUser(UserRequestTO request) {
        String user = usersEndpoint.createUsers(request);
        Gson gson = new Gson();
        return gson.fromJson(user, UserResponseTO.class);
    }
}
