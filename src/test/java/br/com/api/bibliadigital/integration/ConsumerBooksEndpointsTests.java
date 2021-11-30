package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.application.exceptions.ConfilctLimitRequisitionsIPException;
import br.com.api.bibliadigital.application.exceptions.ResourceNotFoundException;
import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import br.com.api.bibliadigital.shared.ValidateComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import static br.com.api.bibliadigital.utils.Constantes.BEARER_TOKEN;
import static br.com.api.bibliadigital.utils.Constantes.URL_BOOKS;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.reflect.Whitebox.invokeMethod;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ConsumerBooksEndpointsTests {

    @Autowired
    private ConsumerBooksEndpoints booksEndpoints;
    @Autowired
    private ValidateComponent component;
    @Autowired
    public GetBearerToken token;
    @Autowired
    public HttpHeadersCreator httpHeaders;

    private String responseEntity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        booksEndpoints = Mockito.spy(booksEndpoints);
        token.setBearerToken(BEARER_TOKEN);
        httpHeaders = new HttpHeadersCreator(token);
        ReflectionTestUtils.setField(booksEndpoints, "url", URL_BOOKS);
        responseEntity = "";
    }

    @Test
    void should_GetBooks() {
        responseEntity = booksEndpoints.getBooks();
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_GetBooksByAbbrev() {
        responseEntity = booksEndpoints.getBookByAbbrev("tt");
        assertNotNull(responseEntity, "Não deve ser null");
    }

    @Test
    void should_CallMethodExchange() throws Exception {
        ResponseEntity<String> exchange = invokeMethod(booksEndpoints, "exchange", URL_BOOKS);
        assertNotNull(exchange.getBody(), "Não deve ser null");
        assertThat(exchange.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    void should_ThrowResourceNotFoundException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> booksEndpoints.getBookByAbbrev("PP"));
        assertEquals(exception.getClass(), ResourceNotFoundException.class);
    }

    @Test
    void should_ThrowInternalServerError500() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class,
                () -> component.validate(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertEquals(exception.getClass(), HttpClientErrorException.class);
    }

    @Test
    void should_ThrowConfilctLimitRequisitionsIPException409() {
        ConfilctLimitRequisitionsIPException exception = assertThrows(ConfilctLimitRequisitionsIPException.class,
                () -> component.validate(new HttpClientErrorException(HttpStatus.CONFLICT)));
        assertEquals(exception.getClass(), ConfilctLimitRequisitionsIPException.class);
    }

}


