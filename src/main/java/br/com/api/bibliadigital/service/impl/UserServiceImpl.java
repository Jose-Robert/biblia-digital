package br.com.api.bibliadigital.service.impl;

import br.com.api.bibliadigital.model.Stats;
import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.model.dto.UserV2;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.integration.ConsumerUsersEndpoints;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ConsumerUsersEndpoints usersEndpoints;
    private final Gson gson;

    public UserServiceImpl(ConsumerUsersEndpoints usersEndpoints, Gson gson) {
        this.usersEndpoints = usersEndpoints;
        this.gson = gson;
    }

    @Override
    public UserResponseTO createUser(UserRequestTO request) {
        String user = usersEndpoints.postUsers(request);
        return gson.fromJson(user, UserResponseTO.class);
    }

    @Override
    public UserV2 findUser(String email) {
        String user = usersEndpoints.getUser(email);
        return gson.fromJson(user, UserV2.class);
    }

    @Override
    public Stats findAllStatistics() {
        String stats = usersEndpoints.getUserStats();
        return gson.fromJson(stats, Stats.class);
    }
}
