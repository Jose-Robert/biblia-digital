package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.model.dto.VersesRequestTO;
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
import static br.com.api.bibliadigital.utils.Constantes.URL_VERSES;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.powermock.reflect.Whitebox.invokeMethod;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ConsumerVersesEndpointsTests {

    @Autowired
    private ConsumerVersesEndpoints versesEndpoints;
    @Autowired
    public GetBearerToken token;
    @Autowired
    public HttpHeadersCreator httpHeaders;

    private String responseEntity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        versesEndpoints = Mockito.spy(versesEndpoints);
        token.setBearerToken(BEARER_TOKEN);
        httpHeaders = new HttpHeadersCreator(token);
        ReflectionTestUtils.setField(versesEndpoints, "url", URL_VERSES);
        responseEntity = "";
    }

    @Test
    void should_getAllVersesAndDetailsOfChapter() {
        String version = "acf"; String abbrev = "is"; Integer chapter = 44;
        responseEntity = versesEndpoints.getAllVersesAndDetailsOfChapter(version, abbrev, chapter);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getVerseByChapter() {
        String version = "acf"; String abbrev = "is"; Integer chapter = 53; Integer number = 10;
        responseEntity = versesEndpoints.getVerseByChapter(version, abbrev, chapter, number);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getVerseRandom() {
        String version = "kjv";
        responseEntity = versesEndpoints.getVerseRandom(version);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_getVerseByBookRandom() {
        String version = "acf";
        String abbrev = "es";
        responseEntity = versesEndpoints.getVerseByBookRandom(version, abbrev);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_searchByWord() {
        VersesRequestTO requestTO = fillVersesRequest();
        responseEntity = versesEndpoints.searchByWord(requestTO);
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_CallMethodExchange_GET() throws Exception {
        ResponseEntity<String> exchange = invokeMethod(versesEndpoints, "exchange", URL_VERSES+"/acf"+"/random");
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    void should_CallMethodExchange_POST() throws Exception {
        VersesRequestTO requestTO = fillVersesRequest();
        ResponseEntity<String> exchange = invokeMethod(versesEndpoints, "exchange", URL_VERSES+"/search", requestTO);
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }

    public VersesRequestTO fillVersesRequest() {
        return VersesRequestTO.builder()
                .version("ra")
                .search("arrependimento")
                .build();
    }
}


