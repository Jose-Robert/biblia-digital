package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.ValidateComponent;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ConsumerBooksEndpoints {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";

    @Value("${urls.biblia-digital.books}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeadersCreator httpHeaders;
    @Autowired
    private ValidateComponent component;

    public String getBooks() {
        try{
            var responseEntity = exchange(url);
            var books = responseEntity.getBody();
            return getResultBody(books);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String getBookByAbbrev(String abbrev) {
        try {
            String newUrl = url + BARRA + abbrev;
            var responseEntity = exchange(newUrl);
            var book = responseEntity.getBody();
            return getResultBody(book);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    private String getResultBody(String body) {
        return !StringUtils.isBlank(body) ? body : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Books");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
    }
}
