package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import br.com.api.bibliadigital.shared.ValidateComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ConsumerVersionsEndpoints {

    public static final String EMPTY_BODY = "Empty Body";

    @Value("${urls.biblia-digital.versions}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeadersCreator httpHeaders;
    @Autowired
    private ValidateComponent component;

    public String getVersions() {
        try {
            var responseEntity = exchange(url);
            return responseEntity.getBody();
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Versions");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
    }
}
