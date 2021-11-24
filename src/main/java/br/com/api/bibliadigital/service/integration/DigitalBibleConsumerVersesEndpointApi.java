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
public class DigitalBibleConsumerVersesEndpointApi {

    public static final String EMPTY_BODY = "Empty Verses";
    public static final String BARRA = "/";

    @Value("${urls.biblia-digital.verses}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

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

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Verses");
        return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    }
}
