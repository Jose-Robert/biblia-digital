package br.com.api.bibliadigital.shared;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class HttpHeadersCreator {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final GetBearerToken token;

    public HttpHeadersCreator(GetBearerToken token) {
        this.token = token;
    }

    public HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER, token.getBearerToken());
        return headers;
    }

    public void getAuthorization(HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("authorization");
        token.setBearerToken(authorization);
        if (!StringUtils.isBlank(authorization)) {
            log.info(AUTHORIZATION_HEADER+": " + token.getBearerToken());
        } else {
            log.info(AUTHORIZATION_HEADER+": " + token.getBearerToken());
        }
    }
}
