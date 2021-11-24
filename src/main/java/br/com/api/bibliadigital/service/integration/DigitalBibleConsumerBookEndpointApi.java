package br.com.api.bibliadigital.service.integration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DigitalBibleConsumerBookEndpointApi {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";

    @Value("${urls.biblia-digital.books}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public String getBooks() {
        var response = exchange(url);
        var books = response.getBody();
        return !StringUtils.isBlank(books) ? books : EMPTY_BODY;
    }

    public String getBookByAbbrev(String abbrev) {
        String newUrl = url + BARRA + abbrev;
        var response = exchange(newUrl);
        var book = response.getBody();
        return !StringUtils.isBlank(book) ? book : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Books");
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }
}
