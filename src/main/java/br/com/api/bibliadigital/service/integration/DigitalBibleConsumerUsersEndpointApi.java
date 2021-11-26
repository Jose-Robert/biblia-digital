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
public class DigitalBibleConsumerUsersEndpointApi {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";

    @Value("${urls.biblia-digital.users}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public String createUsers(Object request) {
        var reponse = exchange(url, request);
        var userBody = reponse.getBody();
        return !StringUtils.isBlank(userBody) ? userBody : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Users");
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }

    private ResponseEntity<String> exchange(String uri, Object request) {
        log.info("Consultando API A BIBLIA DIGITAL - Users Request Create");
        return restTemplate.postForEntity(uri, request, String.class);
    }

}
