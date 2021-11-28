package br.com.api.bibliadigital.integration;

import br.com.api.bibliadigital.shared.GetBearerToken;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ConsumerRequestsEndpoints {

    public static final String EMPTY_BODY = "Empty Body";
    public static final String BARRA = "/";

    @Value("${urls.biblia-digital.requests}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GetBearerToken token;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    public String getAllPeriodRequests(String range) {
        String newUrl = url + BARRA + range;
        var responseEntity = exchange(newUrl);
        var ranges = responseEntity.getBody();
        return getResultBody(ranges);
    }

    public String getRequisitionsByPeriod(String range) {
        String newUrl = url + BARRA + "amount/" + range;
        var responseEntity = exchange(newUrl);
        var ranges = responseEntity.getBody();
        return getResultBody(ranges);
    }

    private String getResultBody(String body) {
        return !StringUtils.isBlank(body) ? body : EMPTY_BODY;
    }

    private ResponseEntity<String> exchange(String uri) {
        log.info("Consultando API A BIBLIA DIGITAL - Requests Period");
        HttpHeaders headers = httpHeaders.createHttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>("headers", headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
    }

}
