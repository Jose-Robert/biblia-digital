package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public String getVersions() {
        var response = exchange(url);
        var versions = response.getBody();
        return !StringUtils.isBlank(versions) ? versions : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Versions");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
    }
}
