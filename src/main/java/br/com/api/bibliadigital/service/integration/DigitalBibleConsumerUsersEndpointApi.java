package br.com.api.bibliadigital.service.integration;

import br.com.api.bibliadigital.utils.ExtractBearerToken;
import br.com.api.bibliadigital.utils.HttpHeadersCreator;
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
public class DigitalBibleConsumerUsersEndpointApi {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${urls.biblia-digital.users}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExtractBearerToken extractBearerToken;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    public String postUsers(Object request) {
        var response = exchange(url, request);
        var userBody = response.getBody();
        return !StringUtils.isBlank(userBody) ? userBody : EMPTY_BODY;
    }

    public String getUser(String email) {
        String newUrl = url + BARRA + email;
        var response = exchange(newUrl);
        var user = response.getBody();
        return !StringUtils.isBlank(user) ? user : EMPTY_BODY;
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
                .header(AUTHORIZATION_HEADER, extractBearerToken.getBearerToken())
                .body(request);
        return restTemplate.postForEntity(uri, requestEntity, String.class);
    }

}
