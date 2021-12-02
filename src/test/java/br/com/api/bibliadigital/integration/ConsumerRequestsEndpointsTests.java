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

import static br.com.api.bibliadigital.utils.Constantes.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.powermock.reflect.Whitebox.invokeMethod;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ConsumerRequestsEndpointsTests {

    @Autowired
    private ConsumerRequestsEndpoints requestsEndpoints;
    @Autowired
    public GetBearerToken token;
    @Autowired
    public HttpHeadersCreator httpHeaders;

    private String responseEntity;
    private String range; //(month, week, day)

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        requestsEndpoints = Mockito.spy(requestsEndpoints);
        token.setBearerToken(BEARER_TOKEN);
        httpHeaders = new HttpHeadersCreator(token);
        ReflectionTestUtils.setField(requestsEndpoints, "url", URL_REQUESTS);
        responseEntity = "";
        range = "02";
    }

    @Test
    void should_getAllPeriodRequests() {
        responseEntity = requestsEndpoints.getAllPeriodRequests(range);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getRequisitionsByPeriod() {
        responseEntity = requestsEndpoints.getRequisitionsByPeriod(range);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_CallMethodExchange() throws Exception {
        ResponseEntity<String> exchange = invokeMethod(requestsEndpoints, "exchange", URL_REQUESTS+"/"+range);
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }
}


