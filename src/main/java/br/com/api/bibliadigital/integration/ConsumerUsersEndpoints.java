package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class ConsumerUsersEndpoints {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${urls.biblia-digital.users}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GetBearerToken token;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    public String postUsers(Object request) {
        var responseEntity = exchange(url, request);
        var userBody = responseEntity.getBody();
        return getResultBody(userBody);
    }

    public String getUser(String email) {
        String newUrl = url + BARRA + email;
        var responseEntity = exchange(newUrl);
        var user = responseEntity.getBody();
        return getResultBody(user);
    }

    public String getUserStats() {
        String newUrl = url + BARRA + "stats";
        var responseEntity = exchange(newUrl);
        var stats = responseEntity.getBody();
        return getResultBody(stats);
    }

    public String updateToken(Object request) {
        String newUrl = url + BARRA + "token";
        var responseEntity = exchangePut(newUrl, request);
        var tokenBody = responseEntity.getBody();
        return getResultBody(tokenBody);
    }

    private String getResultBody(String body) {
        return !StringUtils.isBlank(body) ? body : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Users");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
    }

    private ResponseEntity<String> exchange(String uri, Object request) {
        log.info("Consultando API A BIBLIA DIGITAL - Users Request Create");
        RequestEntity<Object> requestEntity = RequestEntity.post(URI.create(uri))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, token.getBearerToken())
                .body(request);
        return restTemplate.postForEntity(uri, requestEntity, String.class);
    }

    private ResponseEntity<String> exchangePut(String uri, Object request) {
        log.info("Consultando API A BIBLIA DIGITAL - Request Update");
        RequestEntity<Object> requestEntity = RequestEntity.put(URI.create(uri))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request);
        return restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<>(){});
    }

}
