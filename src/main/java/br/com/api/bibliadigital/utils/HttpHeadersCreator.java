package br.com.api.bibliadigital.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class HttpHeadersCreator {

    private final ExtractBearerToken extractBearerToken;

    public HttpHeadersCreator(ExtractBearerToken extractBearerToken) {
        this.extractBearerToken = extractBearerToken;
    }

    public HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", extractBearerToken.getBearerToken());
        return headers;
    }

    public void extractHeaderAuthorization(HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("authorization");
        extractBearerToken.setBearerToken(authorization);
        if (!StringUtils.isBlank(authorization)) {
            log.info("Bearer Token Extraido: " + authorization);
        } else {
            log.info("Não Existe Token..." + authorization);
        }
    }
}
