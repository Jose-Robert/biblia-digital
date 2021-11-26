package br.com.api.bibliadigital.configuration;

import br.com.api.bibliadigital.configuration.property.ApiInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String HTTP_HEADER = "header";
    private static final String SCOPE = "global";
    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Autowired
    private ApiInfoProperties apiInfoProperties;

    @Bean
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public Docket api() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(AUTHORIZATION_HEADER)
                .modelRef(new ModelRef("string"))
                .parameterType(HTTP_HEADER)
                .description("JWT token")
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(apiInfoProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .useDefaultResponseMessages(false);
                    // Para realizar cada requisição é preciso passar o
                    // bearer token como parametro no endpoint
//                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiInfoProperties.getTitle())
                .description(apiInfoProperties.getDescription())
                .version(apiInfoProperties.getVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return List.of(new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, HTTP_HEADER));
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder().securityReferences(securityReferences())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
        return List.of(securityContext);
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { new AuthorizationScope(SCOPE, SCOPE) };
        return List.of((new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes)));
    }
}
