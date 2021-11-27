package br.com.api.bibliadigital.service.integration;

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
        var reponse = exchange(newUrl);
        var verses = reponse.getBody();
        return !StringUtils.isBlank(verses) ? verses : EMPTY_BODY;
    }

    public String getVerseByChapter(String version, String abbrev, Integer chapter, Integer number) {
        String newUrl = url + BARRA + version + BARRA + abbrev + BARRA + chapter + BARRA + number;
        var reponse = exchange(newUrl);
        var verses = reponse.getBody();
        return !StringUtils.isBlank(verses) ? verses : EMPTY_BODY;
    }

    public String getVerseRandom(String version) {
        String newUrl = url + BARRA + version + BARRA + "random";
        var reponse = exchange(newUrl);
        var verse = reponse.getBody();
        return !StringUtils.isBlank(verse) ? verse : EMPTY_BODY;
    }

    public String getVerseByBookRandom(String version, String abbrev) {
        String newUrl = url + BARRA + version + BARRA + abbrev + BARRA + "random";
        var reponse = exchange(newUrl);
        var verses = reponse.getBody();
        return !StringUtils.isBlank(verses) ? verses : EMPTY_BODY;
    }

    public String searchByWord(Object request) {
        String newUrl = url + BARRA + "search";
        var reponse = exchange(newUrl, request);
        var resultSearch = reponse.getBody();
        return !StringUtils.isBlank(resultSearch) ? resultSearch : EMPTY_BODY;
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
