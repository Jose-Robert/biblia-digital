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
public class ConsumerVersesEndpoints {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Value("${urls.biblia-digital.verses}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GetBearerToken token;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    public String getAllVersesAndDetailsOfChapter(String version, String abbrev, Integer chapter) {
        String newUrl = url + BARRA + version + BARRA + abbrev + BARRA + chapter;
        var responseEntity = exchange(newUrl);
        var verses = responseEntity.getBody();
        return getResultBody(verses);
    }

    public String getVerseByChapter(String version, String abbrev, Integer chapter, Integer number) {
        String newUrl = url + BARRA + version + BARRA + abbrev + BARRA + chapter + BARRA + number;
        var responseEntity = exchange(newUrl);
        var verses = responseEntity.getBody();
        return getResultBody(verses);
    }

    public String getVerseRandom(String version) {
        String newUrl = url + BARRA + version + "/random";
        var responseEntity = exchange(newUrl);
        var verse = responseEntity.getBody();
        return getResultBody(verse);
    }

    public String getVerseByBookRandom(String version, String abbrev) {
        String newUrl = url + BARRA + version + BARRA + abbrev + "/random";
        var responseEntity = exchange(newUrl);
        var verses = responseEntity.getBody();
        return getResultBody(verses);
    }

    public String searchByWord(Object request) {
        String newUrl = url + "/search";
        var responseEntity = exchange(newUrl, request);
        var resultSearch = responseEntity.getBody();
        return getResultBody(resultSearch);
    }

    private String getResultBody(String body) {
        return !StringUtils.isBlank(body) ? body : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Verses");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
    }

    private ResponseEntity<String> exchange(String uri, Object request) {
        log.info("Consultando API A BIBLIA DIGITAL - Verses Request Search");
        RequestEntity<Object> requestEntity = RequestEntity.post(URI.create(uri))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER, token.getBearerToken())
                .body(request);
        return restTemplate.postForEntity(uri, requestEntity, String.class);
    }

}
