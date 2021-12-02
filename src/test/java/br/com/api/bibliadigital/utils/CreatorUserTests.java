package br.com.api.bibliadigital.utils;

import br.com.api.bibliadigital.integration.ConsumerUsersEndpoints;
import br.com.api.bibliadigital.model.dto.UserRequestTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static br.com.api.bibliadigital.utils.GeradorNome.gerarNome;

@Component
public class CreatorUserTests {

    @Autowired
    private ConsumerUsersEndpoints usersEndpoints;

    public UserRequestTO fillRequestTO() {
        return UserRequestTO.builder()
                .email(gerarNome() + "@teste.com.br")
                .name("Meu Teste")
                .notifications(true)
                .password("teste1234")
                .build();
    }

    public String getToken(UserRequestTO requestTO) {
        String responseEntity = usersEndpoints.postUsers(requestTO);
        getEmail(responseEntity);
        JSONObject jsonObject = new JSONObject(responseEntity);
        String token = jsonObject.getString("token");
        return "Bearer " + token;
    }

    public String getEmail(String responseEntity) {
        JSONObject jsonObject = new JSONObject(responseEntity);
        String email = jsonObject.getString("email");
        return String.valueOf(email);
    }

}
