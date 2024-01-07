package com.staxrt.tutorial.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.ParameterCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/v1/**") // Paquete donde se encuentra el controlador
                .build();
    }

}
