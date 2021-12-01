package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
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
import static br.com.api.bibliadigital.utils.Constantes.URL_VERSIONS;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.powermock.reflect.Whitebox.invokeMethod;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ConsumerVersionsEndpointsTests {

    @Autowired
    private ConsumerVersionsEndpoints versionsEndpoints;
    @Autowired
    public GetBearerToken token;
    @Autowired
    public HttpHeadersCreator httpHeaders;

    private String responseEntity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        versionsEndpoints = Mockito.spy(versionsEndpoints);
        token.setBearerToken(BEARER_TOKEN);
        httpHeaders = new HttpHeadersCreator(token);
        ReflectionTestUtils.setField(versionsEndpoints, "url", URL_VERSIONS);
        responseEntity = "";
    }

    @Test
    void should_GetVersions() {
        responseEntity = versionsEndpoints.getVersions();
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_CallMethodExchange() throws Exception {
        ResponseEntity<String> exchange = invokeMethod(versionsEndpoints, "exchange", URL_VERSIONS);
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }
}


