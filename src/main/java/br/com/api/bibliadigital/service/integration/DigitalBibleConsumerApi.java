package br.com.api.bibliadigital.service.integration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DigitalBibleConsumerApi {

    public static final String EMPTY_BODY = "Empty Body";

    @Value("${url.api.biblia-digital}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public String getBooks() {
        url = url + "/books";
        var response = exchange(url);
        var books = response.getBody();
        return !StringUtils.isBlank(books) ? books : EMPTY_BODY;
    }

    public String getBookByAbbrev(String abbrev) {
        url = url + "/books/" + abbrev;
        var response = exchange(url);
        var book = response.getBody();
        return !StringUtils.isBlank(book) ? book : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }
}
