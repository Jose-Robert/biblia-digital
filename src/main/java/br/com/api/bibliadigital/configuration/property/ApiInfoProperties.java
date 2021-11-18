package br.com.api.bibliadigital.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("api.biblia-digital.info")
public class ApiInfoProperties {

    private String title;
    private String description;
    private String version;
    private String basePackage;

}
