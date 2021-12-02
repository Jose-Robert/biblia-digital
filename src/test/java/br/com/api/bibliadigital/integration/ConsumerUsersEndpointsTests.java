package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.application.exceptions.TokenNotAuthorizedException;
import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserRequestV2;
import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import br.com.api.bibliadigital.shared.UserValidateComponent;
import br.com.api.bibliadigital.utils.CreatorUserTests;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static br.com.api.bibliadigital.utils.Constantes.BEARER_TOKEN;
import static br.com.api.bibliadigital.utils.Constantes.URL_USERS;
import static br.com.api.bibliadigital.utils.GeradorNome.gerarNome;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.powermock.reflect.Whitebox.invokeMethod;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ConsumerUsersEndpointsTests {

    @Autowired
    private ConsumerUsersEndpoints usersEndpoints;
    @Autowired
    public GetBearerToken token;
    @Autowired
    public HttpHeadersCreator httpHeaders;
    @Autowired
    public CreatorUserTests creatorUser;
    @Autowired
    private UserValidateComponent component;

    private String responseEntity;
    private String email;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        usersEndpoints = Mockito.spy(usersEndpoints);
        creatorUser = Mockito.spy(creatorUser);
        token.setBearerToken(BEARER_TOKEN);
        httpHeaders = new HttpHeadersCreator(token);
        ReflectionTestUtils.setField(usersEndpoints, "url", URL_USERS);
        responseEntity = "";
        email = "usuario@admin.com";
    }

    @Test
    void should_postUsers() {
        UserRequestTO requestTO = fillUser();
        responseEntity = usersEndpoints.postUsers(requestTO);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getUser() {
        responseEntity = usersEndpoints.getUser(email);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getUserStats() {
        responseEntity = usersEndpoints.getUserStats();
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_updateToken() {
        UserRequestV2 requestV2 = this.fillUserRequestV2();
        responseEntity = usersEndpoints.updateToken(requestV2);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_deleteUser() {
        UserRequestV2 requestV2 = new UserRequestV2();
        UserRequestTO requestTO = creatorUser.fillRequestTO();
        requestV2.setEmail(requestTO.getEmail());
        requestV2.setPassword(requestTO.getPassword());

        String bearerToken = creatorUser.getToken(requestTO);
        token.setBearerToken(bearerToken);
        httpHeaders = new HttpHeadersCreator(token);

        responseEntity = usersEndpoints.deleteUser(requestV2);
        JSONObject json = new JSONObject(responseEntity);
        String msg = json.getString("msg");
        assertEquals("User successfully removed", msg);
    }

    @Test
    void should_sendEmail() {
        responseEntity = usersEndpoints.sendEmail(email);
        JSONObject json = new JSONObject(responseEntity);
        String msg = json.getString("msg");
        assertEquals("New password successfully sent to email "+email, msg);
    }

    @Test
    void should_throwUserAlreadyExistsException() {
        token.setBearerToken(null);
        httpHeaders = new HttpHeadersCreator(token);
        try {
            responseEntity = usersEndpoints.getUser(email);
        } catch (TokenNotAuthorizedException errorException) {
            String message = errorException.getMessage();
            String statusCode = message.substring(0,3);
            assertThat(statusCode).isEqualTo("403");
            assertEquals(errorException.getClass(), TokenNotAuthorizedException.class);
        }
    }

    @Test
    void should_CallMethodExchange_POST() throws Exception {
        UserRequestTO requestTO = this.fillUser();
        ResponseEntity<String> exchange = invokeMethod(usersEndpoints, "exchange", URL_USERS, requestTO);
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }

    private UserRequestTO fillUser() {
        return UserRequestTO.builder()
                .email(gerarNome() + "@gmail.com.br")
                .name("ROBERT")
                .password("rob12345")
                .notifications(false)
                .build();
    }

    private UserRequestV2 fillUserRequestV2() {
        return UserRequestV2.builder()
                .email("mmarianeantoniabrito@cvc.com.br")
                .password("mggu3E2LlG")
                .build();
    }

}


