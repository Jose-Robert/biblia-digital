package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import br.com.api.bibliadigital.shared.UserValidateComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
    @Autowired
    private UserValidateComponent component;

    public String postUsers(Object request) {
        try {
            var responseEntity = exchange(url, request);
            var userBody = responseEntity.getBody();
            return getResultBody(userBody);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String getUser(String email) {
        try {
            String newUrl = url + BARRA + email;
            var responseEntity = exchange(newUrl);
            var user = responseEntity.getBody();
            return getResultBody(user);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String getUserStats() {
        try {
            String newUrl = url + "/stats";
            var responseEntity = exchange(newUrl);
            var stats = responseEntity.getBody();
            return getResultBody(stats);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String updateToken(Object request) {
        try {
            String newUrl = url + "/token";
            var responseEntity = exchangePut(newUrl, request);
            var tokenBody = responseEntity.getBody();
            return getResultBody(tokenBody);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String deleteUser(Object request) {
        try {
            var responseEntity = exchangeDelete(url, request);
            var msg = responseEntity.getBody();
            return getResultBody(msg);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    public String sendEmail(String email) {
        try {
            String newUrl = url + "/password" + BARRA + email;
            log.info("Consultando API A BIBLIA DIGITAL - Resend User Password");
            var responseEntity = restTemplate.exchange(newUrl, HttpMethod.POST, null, String.class);
            var msg = responseEntity.getBody();
            return getResultBody(msg);
        } catch (HttpClientErrorException errorException) {
            component.validate(errorException);
        }
        return EMPTY_BODY;
    }

    private String getResultBody(String body) {
        return !StringUtils.isBlank(body) ? body : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Users");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
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
        log.info("Consultando API A BIBLIA DIGITAL - User Request Update");
        RequestEntity<Object> requestEntity = RequestEntity.put(URI.create(uri))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request);
        return restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, new ParameterizedTypeReference<>() {
        });
    }

    private ResponseEntity<String> exchangeDelete(String uri, Object request) {
        log.info("Consultando API A BIBLIA DIGITAL - User Request Delete");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);
    }

}
